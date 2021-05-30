package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class GetShelfStatusResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("hasBooks")
    private ArrayList<BookData> hasBook;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<BookData> getHasBooks() {
        return hasBook;
    }
}
