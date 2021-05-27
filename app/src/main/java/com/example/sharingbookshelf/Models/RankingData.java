package com.example.sharingbookshelf.Models;

public class RankingData {
    private int grade;
    private int bookId;
    private String isbn;
    private String booktitle;

    public int getBookId() { return bookId; }

    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) { this.grade = grade; }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }
}
