package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RankingResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("result")
    ArrayList<RankingData> rankingData;

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

    public ArrayList<RankingData> getRankingData() {
        return rankingData;
    }

    public void setRankingData(ArrayList<RankingData> rankingData) {
        this.rankingData = rankingData;
    }

}
