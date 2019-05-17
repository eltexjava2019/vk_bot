package ru.eltex.vkbot.vkapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.eltex.vkbot.model.Comment;
import ru.eltex.vkbot.model.Post;

import java.util.ArrayList;
import java.util.List;

class JsonParser {

    private JsonParser() {

    }

    static List<Post> getPostsFromJson(String json) {
        JsonObject jsonObject = new com.google.gson.JsonParser().parse(json).getAsJsonObject();
        JsonObject jsonObjectResponse = jsonObject.getAsJsonObject("response");
        JsonArray jsonArray = jsonObjectResponse.getAsJsonArray("items");

        List<Post> posts = new ArrayList<>(jsonArray.size());
        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            int postId = object.get("id").getAsInt();
            String text = object.get("text").getAsString();
            int userId = object.get("from_id").getAsInt();
            int date = object.get("date").getAsInt();

            Post post = new Post();
            post.setPostId(postId);
            post.setText(text);
            post.setUserId(userId);
            post.setDate(date);
            posts.add(post);
        }
        return posts;
    }

    static List<Comment> getCommentsFromJson(String json) {
        JsonObject jsonObject = new com.google.gson.JsonParser().parse(json).getAsJsonObject();
        JsonObject jsonObjectResponse = jsonObject.getAsJsonObject("response");
        JsonArray jsonArray = jsonObjectResponse.getAsJsonArray("items");

        List<Comment> comments = new ArrayList<>(jsonArray.size());
        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            int commentId = object.get("id").getAsInt();
            String text = object.get("text").getAsString();
            int userId = object.get("from_id").getAsInt();
            int date = object.get("date").getAsInt();
            int postId = object.get("post_id").getAsInt();

            Comment comment = new Comment();
            comment.setCommentId(commentId);
            comment.setDate(date);
            comment.setPostId(postId);
            comment.setText(text);
            comment.setUserId(userId);
            comments.add(comment);
        }
        return comments;
    }
}
