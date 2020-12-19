package com.example.onlinenotes.Model;

public class Users {
    String fullName;
    String userName;
    String email;
    String id;

    public Users() {
    }

    public Users(String fullName, String userName, String email, String id) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
