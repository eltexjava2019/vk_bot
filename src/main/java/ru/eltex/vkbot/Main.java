package ru.eltex.vkbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eltex.vkbot.model.Post;
import ru.eltex.vkbot.vkapi.VkApi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    List<Post> posts = VkApi.getAllPosts();
                    VkObjectFilter.filterPosts(posts);
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
}
