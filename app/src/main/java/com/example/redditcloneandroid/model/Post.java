package com.example.redditcloneandroid.model;

public class Post {

    private String community;
    private String title;
    private String text;
    private int avatar;

    public Post() {

    }

    public Post(String community, String title, String text, int avatar) {
        this.community = community;
        this.title = title;
        this.text = text;
        this.avatar = avatar;
    }

    public String getCommunity() {return community;}

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Post{" +
                "community='" + community + '\'' +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
