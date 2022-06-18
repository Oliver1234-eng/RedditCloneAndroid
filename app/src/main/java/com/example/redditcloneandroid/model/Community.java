package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class Community implements Serializable {

    private int id;
    private String name;
    private String description;
    private String rules;

    public Community() {

    }

    public Community(int id, String name, String description, String rules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rules='" + rules + '\'' +
                '}';
    }
}
