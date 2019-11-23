package com.example.blogposting;

public class Post {
    private String category;
    private String description;
    private long timestamp;
    private String title;

    public Post() {

    }

    public Post(String category, String description, long timestamp, String title) {
        this.category = category;
        this.description = description;
        this.timestamp = timestamp;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return Long.toString(timestamp);
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
