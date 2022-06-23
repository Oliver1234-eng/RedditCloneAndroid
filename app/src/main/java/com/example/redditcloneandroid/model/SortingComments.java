package com.example.redditcloneandroid.model;

import java.util.Comparator;

public class SortingComments {

    private String id;
    private String reply;

    public SortingComments(String id, String reply) {
        this.id = id;
        this.reply = reply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public static Comparator<SortingComments> idAscending = new Comparator<SortingComments>() {
        @Override
        public int compare(SortingComments comment1, SortingComments comment2) {
            int id1 = Integer.valueOf(comment1.getId());
            int id2 = Integer.valueOf(comment2.getId());

            return Integer.compare(id1, id2);
        }
    };

    public static Comparator<SortingComments> replyAscending = new Comparator<SortingComments>() {
        @Override
        public int compare(SortingComments comment1, SortingComments comment2) {
            String reply1 = comment1.getReply();
            String reply2 = comment2.getReply();
            reply1 = reply1.toLowerCase();
            reply2 = reply2.toLowerCase();

            return reply1.compareTo(reply2);
        }
    };
}
