package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class BookreportData {

    @SerializedName("book")
    BookData book;

    @SerializedName("bookreport")
    BookreportDetailData bookreports;

    public BookData getBook() {
        return book;
    }

    public BookreportDetailData getBookreports() {
        return bookreports;
    }

    public void setBookreports(BookreportDetailData bookreports) {
        this.bookreports = bookreports;
    }

    /*@SerializedName("bookReports")
    private BookData bookData;

    @SerializedName("book_id")
    private int book_id;

    @SerializedName("canvas_uri")
    private String canvas_uri;

    @SerializedName("contents")
    private String contents;

    @SerializedName("created")
    private Date created;

    private String title;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getCanvas_uri() {
        return canvas_uri;
    }

    public void setCanvas_uri(String canvas_uri) {
        this.canvas_uri = canvas_uri;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
*/
}
