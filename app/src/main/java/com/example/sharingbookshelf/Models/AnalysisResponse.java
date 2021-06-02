package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class AnalysisResponse {

    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("result")
    AnalysisData analysisData;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public AnalysisData getAnalysisData() {
        return analysisData;
    }
}
