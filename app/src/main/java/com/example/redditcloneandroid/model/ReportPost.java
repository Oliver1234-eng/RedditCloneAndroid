package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class ReportPost implements Serializable {

    private int id;
    private String reportReasonPost;
    private int whichPost;

    public ReportPost() {

    }

    public ReportPost(int id, String reportReasonPost, int whichPost) {
        this.id = id;
        this.reportReasonPost = reportReasonPost;
        this.whichPost = whichPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportReasonPost() {
        return reportReasonPost;
    }

    public void setReportReasonPost(String reportReasonPost) {
        this.reportReasonPost = reportReasonPost;
    }

    public int getWhichPost() {
        return whichPost;
    }

    public void setWhichPost(int whichPost) {
        this.whichPost = whichPost;
    }

    @Override
    public String toString() {
        return "ReportPost{" +
                "id=" + id +
                ", reportReasonPost='" + reportReasonPost + '\'' +
                ", whichPost=" + whichPost +
                '}';
    }
}
