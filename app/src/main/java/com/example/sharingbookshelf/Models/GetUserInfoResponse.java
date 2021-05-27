package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class GetUserInfoResponse {
    @SerializedName("flag")
    int flag;

    @SerializedName("photoURL")
    String photoURL;

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

    public String getPhotoURL() {
        return photoURL;
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
