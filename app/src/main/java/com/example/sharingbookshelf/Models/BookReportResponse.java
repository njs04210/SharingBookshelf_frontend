package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookReportResponse {

    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("bookReports")
    ArrayList<BookreportData> bookReports;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<BookreportData> getBookReports() {
        return bookReports;
    }
}
