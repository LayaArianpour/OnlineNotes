package com.example.onlinenotes.Model;

public class Notes {
    String noteId;
    String textNote;
    String publisher;
    String title;
    String DateTime;

    public Notes(String noteId, String textNote, String publisher, String title, String dateTime) {
        this.noteId = noteId;
        this.textNote = textNote;
        this.publisher = publisher;
        this.title = title;
        DateTime = dateTime;
    }

    public Notes() {
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
}
