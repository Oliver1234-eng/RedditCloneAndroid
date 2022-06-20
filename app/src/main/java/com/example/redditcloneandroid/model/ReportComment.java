package com.example.redditcloneandroid.model;

import java.io.Serializable;

public class ReportComment implements Serializable {

    private int id;
    private String reportReasonComment;
    private int whichComment;

    public ReportComment() {

    }

    public ReportComment(int id, String reportReasonComment, int whichComment) {
        this.id = id;
        this.reportReasonComment = reportReasonComment;
        this.whichComment = whichComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ReportComment{" +
                "id=" + id +
                ", reportReasonComment='" + reportReasonComment + '\'' +
                ", whichComment=" + whichComment +
                '}';
    }
}
