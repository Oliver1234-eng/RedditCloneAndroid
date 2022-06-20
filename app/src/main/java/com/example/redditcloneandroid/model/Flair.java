package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class Flair implements Serializable {

    private int id;
    private String flairName;

    public Flair() {

    }

    public Flair(int id, String flairName) {
        this.id = id;
        this.flairName = flairName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlairName() {
        return flairName;
    }

    public void setFlairName(String flairName) {
        this.flairName = flairName;
    }

    @Override
    public String toString() {
        return "Flair{" +
                "id=" + id +
                ", flairName='" + flairName + '\'' +
                '}';
    }
}
