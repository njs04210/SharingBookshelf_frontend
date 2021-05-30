package com.example.sharingbookshelf.Models;

import android.service.autofill.UserData;

import com.google.gson.annotations.SerializedName;

public class RankingData {

    @SerializedName("ranking")
    int ranking;

    @SerializedName("total")
    int total;

    @SerializedName("book")
    BookData book;

    @SerializedName("user")
    GetUserInfoResponse user;

    @SerializedName("kids")
    KidsData kids;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public GetUserInfoResponse getUser() {
        return user;
    }

    public void setUser(GetUserInfoResponse user) {
        this.user = user;

    }

    public KidsData getKids() {
        return kids;
    }

    public void setKids(KidsData kids) {
        this.kids = kids;
    }
}
