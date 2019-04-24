package ru.eltex.vkbot.vkapi;

import ru.eltex.vkbot.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.eltex.vkbot.vkapi.VkApiProperties.getProperty;

public class VkApi {

    private static final String BASE_API_ADDRESS = "https://api.vk.com/method/";

    private VkApi() {

    }

    public static List<Post> getAllPosts() throws IOException {
        String requestUrl = createRequestUrl("wall.get", Arrays.asList("v=5.95",
                "owner_id=" + getProperty("group_id"),
                "access_token=" + getProperty("bot_service_key")));

        URL url = new URL(requestUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        bufferedReader.close();
        return JsonParser.getPostsFromJson(response);
    }

    private static String createRequestUrl(String methodName, List<String> params) {
        if (params.isEmpty()) {
            return BASE_API_ADDRESS + methodName;
        }
        StringBuilder stringBuilder = new StringBuilder(BASE_API_ADDRESS);
        stringBuilder.append(methodName);
        stringBuilder.append("?");
        for (String param : params) {
            stringBuilder.append(param);
            stringBuilder.append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
