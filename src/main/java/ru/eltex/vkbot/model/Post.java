package ru.eltex.vkbot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String text;

    @Column(name = "user_id")
    private int userId;
    private int date;

    @Transient
    private transient boolean removePost;

    public Post() {

    }

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
