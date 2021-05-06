package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class CreateShelfResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
