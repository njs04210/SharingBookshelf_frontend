package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserInfoResponse implements Serializable {

    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("user")
    UserData user;

    @SerializedName("kids")
    KidsData kids;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public UserData getUser() {
        return user;
    }

    public KidsData getKids() {
        return kids;
    }

    /*@SerializedName("photoURL")
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
    }*/
}
