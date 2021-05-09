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
    private ArrayList<Map<String,Object>> hasBook;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Map<String,Object>> getHasBooks() {
        return hasBook;
    }

}
