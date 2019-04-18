package ru.eltex.vkbot.model;

public class Attachment {

    private String type;
    private Photo photo;

    public Attachment(String type, Photo photo) {
        this.type = type;
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }
}
