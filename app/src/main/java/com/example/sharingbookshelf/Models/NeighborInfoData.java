package com.example.sharingbookshelf.Models;

import java.util.ArrayList;

public class NeighborInfoData {
    private String nickname;
    private int profile;
    ArrayList<NeighborShelfData> arrayList;

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

    public ArrayList<NeighborShelfData> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<NeighborShelfData> arrayList) {
        this.arrayList = arrayList;
    }
}
