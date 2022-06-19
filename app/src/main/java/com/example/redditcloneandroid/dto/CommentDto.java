package com.example.redditcloneandroid.dto;

public class CommentDto {

    private String reply;
    private int post;

    public CommentDto() {

    }

    public CommentDto(String reply, int post) {
        this.reply = reply;
        this.post = post;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }
}
