package ru.eltex.vkbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    private int count;
    @SerializedName("items")
    private List<Post> posts;

    public Response(int count, List<Post> posts) {
        this.count = count;
        this.posts = posts;
    }

    public int getCount() {
        return count;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
