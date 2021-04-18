package com.example.sharingbookshelf.Models;

public class NeighborShelfInfoData {
    private String nickname;
    private int profile;

    public NeighborShelfInfoData(int profile, String title){
        this.profile = profile;
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }
    public int getProfile(){
        return profile;
    }
}
