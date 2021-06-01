package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class AnalysisResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("result")
    AnalysisData analysisData;
}
