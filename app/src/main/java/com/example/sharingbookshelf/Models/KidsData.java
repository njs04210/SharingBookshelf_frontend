package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

public class KidsData {
    @SerializedName("age")
    int age;

    @SerializedName("sex")
    int sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
