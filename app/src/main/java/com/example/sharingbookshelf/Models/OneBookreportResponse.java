package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class OneBookreportResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("report")
    BookreportDetailData bookreportDetailData;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BookreportDetailData getBookreportDetailData() {
        return bookreportDetailData;
    }

    public void setBookreportDetailData(BookreportDetailData bookreportDetailData) {
        this.bookreportDetailData = bookreportDetailData;
    }
}
