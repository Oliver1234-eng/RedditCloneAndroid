package com.example.redditcloneandroid.model;

public class User {

    private String ime;
    private String prezime;
    private String email;

    public User() {

    }

    public User(String ime, String prezime, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ime: " + ime + "\n"  +
                "prezime: " + prezime + "\n" +
                "email: " + email + "\n";
    }
}
