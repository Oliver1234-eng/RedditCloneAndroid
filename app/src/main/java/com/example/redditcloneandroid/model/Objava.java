package com.example.redditcloneandroid.model;


public class Objava {

    private Long id;
    private String title;
    private String text;
    private String community;

    //public Objava(Long id, String title, String text, String community) {
        //this.id = id;
        //this.title = title;
        //this.text = text;
        //this.community = community;
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    @Override
    public String toString() {
        return "Objava{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", community='" + community + '\'' +
                '}';
    }
}
