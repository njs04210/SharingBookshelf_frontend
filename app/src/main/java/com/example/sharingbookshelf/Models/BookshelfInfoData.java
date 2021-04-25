package com.example.sharingbookshelf.Models;

import java.util.ArrayList;

public class BookshelfInfoData {
    private String nickname;
    private int profile;
    private ArrayList<BookData> bookList;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public ArrayList<BookData> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<BookData> bookList) {
        this.bookList = bookList;
    }
}
