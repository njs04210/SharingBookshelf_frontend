package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryData {
    @SerializedName("category_id")
    int category_id;

    @SerializedName("total")
    int total;

    public int getCategory_id() {
        return category_id;
    }

    public int getTotal() {
        return total;
    }
}
