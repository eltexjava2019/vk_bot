package ru.eltex.vkbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eltex.vkbot.model.Post;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

class VkObjectFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(VkObjectFilter.class);
    private static final File DICTIONARY_FILE;
    private static List<String> dictionary;

    static {
        DICTIONARY_FILE = new File(VkObjectFilter.class.getResource("/dictionary").getPath());
    }

    private VkObjectFilter() {

    }

    private static void readDictionaryFromFile() {
        try {
            dictionary = Files.readAllLines(DICTIONARY_FILE.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Error loading dictionary file " + DICTIONARY_FILE, e);
        }
    }

    static synchronized void filterPosts(List<Post> posts) {
        if (dictionary == null) {
            readDictionaryFromFile();
        }
        for (Post post : posts) {
            for (String word: dictionary) {
                if (post.getText().contains(word)) {
                    post.setRemovePost(true);
                    break;
                }
            }
        }

    }
}
