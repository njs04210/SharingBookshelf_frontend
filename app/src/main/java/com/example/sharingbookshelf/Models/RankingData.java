package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class RankingData {
    @SerializedName("ranking")
    int ranking;

    @SerializedName("total")
    int total;

    @SerializedName("book")
    BookData book;

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

}
