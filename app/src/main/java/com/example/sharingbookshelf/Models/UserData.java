package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("nickname")
    String nickname;

    @SerializedName("photoURL")
    String photoURL;

    @SerializedName("area")
    String area;

    @SerializedName("email")
    String email;

    @SerializedName("name")
    String name;

    @SerializedName("created")
    String created;

    public String getNickname() {
        return nickname;
    }

    public String getPhotoURL() {
        return photoURL;
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

    public String getCreated() {
        return created;
    }
}
