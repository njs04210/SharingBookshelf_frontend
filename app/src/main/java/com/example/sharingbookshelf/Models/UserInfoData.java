package com.example.sharingbookshelf.Models;

public class UserInfoData {

    String nickname;
    String age;
    String address;
    String photoURL;
    int sex;

    public UserInfoData(String nickname, String address, String age, int sex, String photoURL) {
        this.nickname = nickname;
        this.age = age;
        this.address = address;
        this.sex = sex;
        this.photoURL = photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

}
