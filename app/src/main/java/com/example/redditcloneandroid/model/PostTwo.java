package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class PostTwo implements Serializable {

    private int id;
    private String title;
    private String text;
    private int community;

    public PostTwo() {

    }

    public PostTwo(int id, String title, String text, int community) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.community = community;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCommunity() {
        return community;
    }

    public void setCommunity(int community) {
        this.community = community;
    }

    @Override
    public String toString() {
        return "PostTwo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", community=" + community +
                '}';
    }
}
