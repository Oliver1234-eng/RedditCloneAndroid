package com.example.redditcloneandroid.dto;

public class PostOneDto {

    private String title;
    private String text;
    private int community;

    public PostOneDto() {

    }

    public PostOneDto(String title, String text, int community) {
        this.title = title;
        this.text = text;
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

    public int getCommunity() {
        return community;
    }

    public void setCommunity(int community) {
        this.community = community;
    }
}
