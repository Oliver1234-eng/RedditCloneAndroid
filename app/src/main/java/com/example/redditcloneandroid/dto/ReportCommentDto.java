package com.example.redditcloneandroid.dto;

public class ReportCommentDto {

    private String reportReasonComment;
    private int whichComment;

    public ReportCommentDto() {

    }

    public ReportCommentDto(String reportReasonComment, int whichComment) {
        this.reportReasonComment = reportReasonComment;
        this.whichComment = whichComment;
    }

    public String getReportReasonComment() {
        return reportReasonComment;
    }

    public void setReportReasonComment(String reportReasonComment) {
        this.reportReasonComment = reportReasonComment;
    }

    public int getWhichComment() {
        return whichComment;
    }

    public void setWhichComment(int whichComment) {
        this.whichComment = whichComment;
    }
}
