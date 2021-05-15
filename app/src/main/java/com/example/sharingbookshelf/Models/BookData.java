package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookData implements Serializable {

    @SerializedName("book_id")
    private int book_id;

    @SerializedName("ISBN")
    private String ISBN;

    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("category")
    private String category;

    @SerializedName("thumbnail")
    private String thumbnail;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /*private int iv_book;

    public int getIv_book() {
        return iv_book;
    }

    public void setIv_book(int iv_book) {
        this.iv_book = iv_book;
    }*/
}
