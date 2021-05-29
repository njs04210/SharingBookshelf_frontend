package com.example.sharingbookshelf.Models;

import java.io.Serializable;

public class fightData implements Serializable {
    private int profileImage;
    private String userName;
    private String fightMessage;
    private String FightTime;
    private String Fightaim;

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFightMessage() {
        return fightMessage;
    }

    public void setFightMessage(String fightMessage) {
        this.fightMessage = fightMessage;
    }

    public String getFightTime() {
        return FightTime;
    }

    public void setFightTime(String fightTime) {
        FightTime = fightTime;
    }

    public String getFightaim() {
        return Fightaim;
    }

    public void setFightaim(String fightaim) {
        Fightaim = fightaim;
    }
}