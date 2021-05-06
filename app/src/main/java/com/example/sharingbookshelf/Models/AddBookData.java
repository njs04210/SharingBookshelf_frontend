package com.example.sharingbookshelf.Models;

import java.util.HashMap;

public class AddBookData {
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    //private String category;
    private String thumbnail;

    public AddBookData(HashMap<String, Object> parameters) {
        this.ISBN = (String) parameters.get("ISBN");
        this.title = (String) parameters.get("title");
        this.author = (String) parameters.get("author");
        this.publisher = (String) parameters.get("publisher");
        this.thumbnail = (String) parameters.get("thumbnail");
    }

}
