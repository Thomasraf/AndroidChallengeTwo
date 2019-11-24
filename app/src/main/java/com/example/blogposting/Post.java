package com.example.blogposting;

public class Post implements Comparable{
    private String category;
    private String description;
    private String timestamp;
    private String title;

    public Post() {

    }

    public Post(String category, String description, String timestamp, String title) {
        this.category = category;
        this.description = description;
        this.timestamp = timestamp;
        this.title = title;
    }

    public Post(String category, String description, long timestamp, String title) {
        this.category = category;
        this.description = description;
        this.timestamp = Long.toString(timestamp);
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
        return timestamp;
    }

    public long getLongTimestamp() {
        return Long.parseLong(timestamp);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

//    public void setTimestamp(long timestamp) {
//        this.timestamp = Long.toString(timestamp);
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Object o) {
        long compareTs = Long.parseLong(((Post)o).getTimestamp());

        /* For Ascending order*/
        //return (int) Math.abs(Long.parseLong(this.timestamp) - compareTs);

        /* For Descending order do like this */
        return (int) (compareTs - Math.abs(Long.parseLong(this.timestamp)));
    }

}
