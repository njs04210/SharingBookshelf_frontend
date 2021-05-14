package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class MemoData {

    @SerializedName("ISBN")
    String ISBN;

    @SerializedName("content")
    String content;

    @SerializedName("created")
    String created;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
