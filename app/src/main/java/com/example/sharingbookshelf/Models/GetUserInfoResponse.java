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

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public void setKids(KidsData kids) {
        this.kids = kids;
    }

}
