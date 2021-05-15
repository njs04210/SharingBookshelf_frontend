package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class MemoData {

    @SerializedName("bookId")
    int bookId;

    @SerializedName("content")
    String content;

    @SerializedName("created")
    String created;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
