package ru.eltex.vkbot.model;

import java.io.Serializable;

public class Post implements Serializable {

    private String text;
    private int userId;
    private int date;
    private transient boolean removePost;

    public Post(String text, int userId, int date) {
        this.text = text;
        this.userId = userId;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public int getDate() {
        return date;
    }

    public boolean isRemovePost() {
        return removePost;
    }

    public void setRemovePost(boolean value) {
        removePost = value;
    }
}
