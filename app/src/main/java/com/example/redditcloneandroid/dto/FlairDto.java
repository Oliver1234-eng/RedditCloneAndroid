package com.example.redditcloneandroid.dto;

public class FlairDto {

    private String flairName;

    public FlairDto() {

    }

    public FlairDto(String flairName) {
        this.flairName = flairName;
    }

    public String getFlairName() {
        return flairName;
    }

    public void setFlairName(String flairName) {
        this.flairName = flairName;
    }
}
