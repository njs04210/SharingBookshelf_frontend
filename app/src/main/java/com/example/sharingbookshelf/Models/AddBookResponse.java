package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class AddBookResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
