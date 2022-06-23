package com.example.redditcloneandroid.model;

import java.util.Comparator;

public class SortingPosts {

    private String id;
    private String title;

    public SortingPosts(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Comparator<SortingPosts> idAscending = new Comparator<SortingPosts>() {
        @Override
        public int compare(SortingPosts post1, SortingPosts post2) {
            int id1 = Integer.valueOf(post1.getId());
            int id2 = Integer.valueOf(post2.getId());

            return Integer.compare(id1, id2);
        }
    };

    public static Comparator<SortingPosts> titleAscending = new Comparator<SortingPosts>() {
        @Override
        public int compare(SortingPosts post1, SortingPosts post2) {
            String title1 = post1.getTitle();
            String title2 = post2.getTitle();
            title1 = title1.toLowerCase();
            title2 = title2.toLowerCase();

            return title1.compareTo(title2);
        }
    };
}
