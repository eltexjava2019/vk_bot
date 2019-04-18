package ru.eltex.vkbot.model;

public class Size {

    private String type;
    private String url;
    private int width;
    private int height;

    public Size(String type, String url, int width, int height) {
        this.type = type;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
