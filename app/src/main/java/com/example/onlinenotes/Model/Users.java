package com.example.onlinenotes.Model;

public class Users {
    String noteId;
    String textNote;
    String publisher;
    String title;
    String DateTime;
    String fullName;
    String userName;
    String email;
    String id;

    Users(){}

    public Users(String noteId, String textNote, String publisher, String title, String DateTime) {
        this.noteId = noteId;
        this.textNote = textNote;
        this.publisher = publisher;
        this.title = title;
        this.DateTime = DateTime;
    }

    public Users(String noteId, String textNote, String publisher, String title, String dateTime, String fullName, String userName, String email, String id) {
        this.noteId = noteId;
        this.textNote = textNote;
        this.publisher = publisher;
        this.title = title;
        DateTime = dateTime;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.id = id;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
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
