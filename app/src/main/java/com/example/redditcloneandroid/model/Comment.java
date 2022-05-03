package com.example.redditcloneandroid.model;

public class Comment {

    private String tekst;
    private String datum;

    public Comment() {

    }

    public Comment(String tekst, String datum) {
        this.tekst = tekst;
        this.datum = datum;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "tekst='" + tekst + '\'' +
                ", datum='" + datum + '\'' +
                '}';
    }
}
