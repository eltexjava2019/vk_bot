package ru.eltex.vkbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.eltex.vkbot.database.CommentRepository;
import ru.eltex.vkbot.database.PostRepository;
import ru.eltex.vkbot.filter.VkObjectFilter;
import ru.eltex.vkbot.model.Comment;
import ru.eltex.vkbot.model.Post;
import ru.eltex.vkbot.vkapi.VkApi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private PostRepository postDB;
    @Autowired
    private CommentRepository commentDB;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    List<Post> posts = VkApi.getAllPosts();
                    filterPosts(posts);
                    filterComments(posts);
                    Thread.sleep(TimeUnit.MINUTES.toMillis(1));
                }
            } catch (IOException e) {
                LOGGER.error("IOException: {}", e.getMessage());
            } catch (InterruptedException e) {
                Thread currentThread = Thread.currentThread();
                currentThread.interrupt();

                LOGGER.error("InterruptedException: {}", e.getMessage());
            }
        });
        thread.start();
    }

    private void filterPosts(List<Post> posts) throws IOException {
        Iterator<Post> postIterator = posts.iterator();
        while (postIterator.hasNext()) {
            Post post = postIterator.next();
            VkObjectFilter.filterObject(post);
            if (post.isRemovePost()) {
                postDB.save(post);
                VkApi.removePostOnWall(post);
                postIterator.remove();
            }
        }
    }

    private void filterComments(List<Post> posts) throws IOException {
        for (Post post : posts) {
            List<Comment> comments = VkApi.getCommentsFromPost(post);
            Iterator<Comment> commentIterator = comments.iterator();
            while (commentIterator.hasNext()) {
                Comment comment = commentIterator.next();
                VkObjectFilter.filterObject(comment);
                if (comment.isRemoveComment()) {
                    commentDB.save(comment);
                    VkApi.removeCommentOnPost(comment);
                    commentIterator.remove();
                }
            }
        }

    }
}
