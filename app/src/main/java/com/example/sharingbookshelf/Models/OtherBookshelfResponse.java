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
    ArrayList<OtherShelfData> result;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<OtherShelfData> getResult() {
        return result;
    }

    public class OtherShelfData {
        @SerializedName("user")
        UserData user;

        @SerializedName("kids")
        KidsData kids;

        @SerializedName("hasBookList")
        ArrayList<BookData> hasBookList;

        public UserData getUser() {
            return user;
        }

        public KidsData getKids() {
            return kids;
        }

        public ArrayList<BookData> getHasBookList() {
            return hasBookList;
        }
    }


}
