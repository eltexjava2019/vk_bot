package ru.eltex.vkbot.model;

import java.util.List;

public class Post {

    private int id;
    private int fromId;
    private int ownerId;
    private int date;
    private String postType;
    private String text;
    private List<Attachment> attachments;
    private Comments comments;
    private Likes likes;
    private Reposts reposts;

    public Post(int id, int fromId, int ownerId, int date, String postType, String text,
                List<Attachment> attachments, Comments comments, Likes likes, Reposts reposts) {
        this.id = id;
        this.fromId = fromId;
        this.ownerId = ownerId;
        this.date = date;
        this.postType = postType;
        this.text = text;
        this.attachments = attachments;
        this.comments = comments;
        this.likes = likes;
        this.reposts = reposts;
    }

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getDate() {
        return date;
    }

    public String getPostType() {
        return postType;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Comments getComments() {
        return comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }
}
