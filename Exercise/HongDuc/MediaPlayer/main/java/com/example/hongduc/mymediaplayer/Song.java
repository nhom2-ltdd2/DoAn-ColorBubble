package com.example.hongduc.mymediaplayer;

public class Song {
    public Song(String title, int file) {
        this.title = title;
        this.file = file;
    }

    private String title;
    private int file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
