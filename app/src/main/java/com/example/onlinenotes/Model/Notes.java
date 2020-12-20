package com.example.onlinenotes.Model;

public class Notes {
    String noteId;
    String textNote;
    String publisher;
    String title;
    String dateTime;
    String dateTimePersian;

    public Notes(String noteId, String textNote, String publisher, String title, String dateTime, String dateTimePersian) {
        this.noteId = noteId;
        this.textNote = textNote;
        this.publisher = publisher;
        this.title = title;
        this.dateTime = dateTime;
        this.dateTimePersian = dateTimePersian;
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
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimePersian() {
        return dateTimePersian;
    }

    public void setDateTimePersian(String dateTimePersian) {
        this.dateTimePersian = dateTimePersian;
    }
}
