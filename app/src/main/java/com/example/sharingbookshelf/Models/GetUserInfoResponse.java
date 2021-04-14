package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class GetUserInfoResponse {
    @SerializedName("flag")
    int flag;

    @SerializedName("profileImg")
    String profileImg;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("area")
    String area;

    @SerializedName("email")
    String email;

    @SerializedName("name")
    String name;

    public int getFlag() {
        return flag;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getNickname() {
        return nickname;
    }

    public String getArea() {
        return area;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
