package ru.eltex.vkbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.eltex.vkbot.database.PostRepository;
import ru.eltex.vkbot.model.Post;
import ru.eltex.vkbot.vkapi.VkApi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private PostRepository postDB;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    filterPosts();
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

    private void filterPosts() throws IOException {
        List<Post> posts = VkApi.getAllPosts();
        VkObjectFilter.filterPosts(posts);
        posts.stream()
                .filter(Post::isRemovePost)
                .forEach(post -> {
                    postDB.save(post);
                    try {
                        VkApi.removePostOnWall(post);
                    } catch (IOException e) {
                        // TODO что-то сделать с этим, пока что временное решение
                        LOGGER.error("IOException: {}", e.getMessage());
                    }
                });
    }
}
