package ru.eltex.vkbot.model;

import ru.eltex.vkbot.filter.FilterObject;

@SuppressWarnings("unused")
public class Comment implements FilterObject {

    private int id;
    private int userId;
    private int postId;
    private int commentId;
    private int date;
    private String text;
    private transient boolean removeComment;

    @Override
    public void setToRemove(boolean value) {
        removeComment = value;
    }

    @Override
    public String getTextToFilter() {
        return text;
    }

    public boolean isRemoveComment() {
        return removeComment;
    }

    public void setRemoveComment(boolean removeComment) {
        this.removeComment = removeComment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
