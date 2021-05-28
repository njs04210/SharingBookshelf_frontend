package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class BookreportDetailData {
    @SerializedName("bookreport_id")
    int bookreport_id;

    @SerializedName("item_id")
    int item_id;

    @SerializedName("contents")
    String contents;

    @SerializedName("canvas_uri")
    String canvas_uri;

    @SerializedName("created")
    String created;

    public int getBookreport_id() {
        return bookreport_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getContents() {
        return contents;
    }

    public String getCanvas_uri() {
        return canvas_uri;
    }

    public String getCreated() {
        return created;
    }

    public void setBookreport_id(int bookreport_id) {
        this.bookreport_id = bookreport_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCanvas_uri(String canvas_uri) {
        this.canvas_uri = canvas_uri;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
