package com.example.redditcloneandroid.dto;

public class ReportPostDto {

    private String reportReasonPost;
    private int whichPost;

    public ReportPostDto() {

    }

    public ReportPostDto(String reportReasonPost, int whichPost) {
        this.reportReasonPost = reportReasonPost;
        this.whichPost = whichPost;
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
}
