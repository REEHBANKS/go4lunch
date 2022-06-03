package com.banks.go4lunch.model;

public class User {

    private String uid;
    private String username;
    private String userMail;


    public User() { }

    public User(String uid, String username,String userMail) {
        this.uid = uid;
        this.username = username;
        this.userMail = userMail;

    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    public String getUserMail() { return userMail; }


    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setUserMail(String userMail) { this.userMail = userMail; }

}
