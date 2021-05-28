package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SelectBookReportResponse {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("books_NoReport")
    ArrayList<SelectBookReportData> books_NoReport;

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

    public ArrayList<SelectBookReportData> getBooks_NoReport() {
        return books_NoReport;
    }

    public void setBooks_NoReport(ArrayList<SelectBookReportData> books_NoReport) {
        this.books_NoReport = books_NoReport;
    }

    public class SelectBookReportData {
        @SerializedName("item_id")
        int item_id;

        @SerializedName("book")
        BookData book;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public BookData getBook() {
            return book;
        }

        public void setBook(BookData book) {
            this.book = book;
        }
    }
}
