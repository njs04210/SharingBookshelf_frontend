package com.example.sharingbookshelf.Models;

import java.util.ArrayList;

public class NeighborInfoData {
    private String nickname;
    private int profile;
    private ArrayList<NeighborShelfData> bookList;

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

    public ArrayList<NeighborShelfData> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<NeighborShelfData> bookList) {
        this.bookList = bookList;
    }
}
