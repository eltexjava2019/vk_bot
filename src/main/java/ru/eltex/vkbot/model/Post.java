package ru.eltex.vkbot.model;

import ru.eltex.vkbot.filter.FilterObject;

import javax.persistence.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post implements FilterObject {

    @Id
    @GeneratedValue
    private int id;

    private int postId;
    private String text;

    @Column(name = "user_id")
    private int userId;
    private int date;

    @Transient
    private transient boolean removePost;

    @Override
    public void setToRemove(boolean value) {
        removePost = value;
    }

    @Override
    public String getTextToFilter() {
        return text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isRemovePost() {
        return removePost;
    }

    public void setRemovePost(boolean value) {
        removePost = value;
    }
}
