package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class CommentGuest implements Serializable {

    private int id;
    private String reply;
    private int post;

    public CommentGuest() {

    }

    public CommentGuest(int id, String reply, int post) {
        this.id = id;
        this.reply = reply;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CommentGuest{" +
                "id=" + id +
                ", reply='" + reply + '\'' +
                ", post=" + post +
                '}';
    }
}
