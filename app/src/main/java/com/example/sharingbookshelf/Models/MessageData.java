package com.example.sharingbookshelf.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Member;

public class MessageData implements Serializable {

  /*  @SerializedName("sender")
    private UserInfoData sender;

    @SerializedName("receiver")
    private UserInfoData receiver;

    @SerializedName("content")
    private String content;

    @SerializedName("created")
    private String created;

    public UserInfoData getSender() {
        return sender;
    }

    public void setSender(UserInfoData sender) {
        this.sender = sender;
    }

    public UserInfoData getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfoData receiver) {
        this.receiver = receiver;
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
    }*/

    private int profileImage;
    private String userName;
    private String lastMessage;
    private String messageTime;

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

}
