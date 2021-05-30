package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class UserReportData {
    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("created")
    private String created;

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() { return thumbnail; }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreated() { return created; }

    public void setCreated(String created) {
        this.created = created;
    }
}
