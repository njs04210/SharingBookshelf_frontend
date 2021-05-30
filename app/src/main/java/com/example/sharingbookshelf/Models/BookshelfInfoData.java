package com.example.sharingbookshelf.Models;

import java.util.ArrayList;

public class BookshelfInfoData {
    private String nickname;
    private String profile;
    private ArrayList<BookData> bookList;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public ArrayList<BookData> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<BookData> bookList) {
        this.bookList = bookList;
    }
}
