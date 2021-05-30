package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OtherBookshelfResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("result")
    List<OtherShelfData> result;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<OtherShelfData> getResult() {
        return result;
    }

    public class OtherShelfData {
        @SerializedName("member")
        GetUserInfoResponse member;

        @SerializedName("hasBookList")
        List<BookData> hasBookList;

        public GetUserInfoResponse getMember() {
            return member;
        }

        public List<BookData> getHasBookList() {
            return hasBookList;
        }
    }


}
