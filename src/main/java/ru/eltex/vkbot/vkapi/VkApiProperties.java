package ru.eltex.vkbot.vkapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

class VkApiProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(VkApiProperties.class);
    private static final String PROPERTIES_FILE = "/vkapi.properties";

    private static Properties properties;

    private VkApiProperties() {

    }

    static synchronized String getProperty(String key) {
        if (properties == null) {
            initProperties();
        }
        return properties.getProperty(key);
    }

    private static void initProperties() {
        properties = new Properties();
        try (InputStream inputStream = VkApiProperties.class.getResourceAsStream(PROPERTIES_FILE);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            LOGGER.error("Error loading properties file " + PROPERTIES_FILE, e);
        }
    }
}
