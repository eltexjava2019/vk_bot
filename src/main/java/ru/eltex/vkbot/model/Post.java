package ru.eltex.vkbot.model;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

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

    private void deleteThisPost() {
        System.out.println("delete post with text:\n" + text);
        //TODO
    }

    public void filter() {
        String[] strings = readFromDictionary();
        int sizeStrings = strings.length;
        for(int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                sizeStrings = i;
                break;
            }
        }
        for(int i = 0; i < sizeStrings; i++) {
            if (text.contains(strings[i])) {
                deleteThisPost();
                break;
            }
        }
    }

    private String[] readFromDictionary() {
        final int MAX_COUNT_WORD_IN_DICTIONARY = 5000;
        String[] strings = new String[MAX_COUNT_WORD_IN_DICTIONARY];
        int i = 0;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dictionary");
        try {
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNext()) {
                strings[i] = scanner.next();
                i++;
                if (i == MAX_COUNT_WORD_IN_DICTIONARY - 1) {
                    //TODO: logger: big dictionary
                    break;
                }
            }
        } catch (NullPointerException ex) {
            //TODO: logger: dictionary not found
        }
        if (i == 0) {
            //TODO: logger: dictionary empty
        }
        return strings;
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
