package ru.eltex.vkbot.model;

import java.util.List;

public class Photo {

    private int id;
    private int albumId;
    private int ownerId;
    private List<Size> sizes;
    private String text;
    private int date;
    private int postId;

    public Photo(int id, int albumId, int ownerId, List<Size> sizes, String text, int date, int postId) {
        this.id = id;
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.sizes = sizes;
        this.text = text;
        this.date = date;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public String getText() {
        return text;
    }

    public int getDate() {
        return date;
    }

    public int getPostId() {
        return postId;
    }
}
