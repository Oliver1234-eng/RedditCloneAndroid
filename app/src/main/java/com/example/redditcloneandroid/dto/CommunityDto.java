package com.example.redditcloneandroid.dto;

import com.example.redditcloneandroid.model.Community;

public class CommunityDto {

    private String name;
    private String description;
    private String rules;

    public CommunityDto() {

    }

    public CommunityDto(String name, String description, String rules) {
        this.name = name;
        this.description = description;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
