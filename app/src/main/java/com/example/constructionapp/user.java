package com.example.constructionapp;

import android.net.Uri;
import android.provider.ContactsContract;

import com.google.android.gms.tasks.Task;

public class user {

    public String username, email, phone;
    String ImageUrl;


    public user() {
    }

    public user(String username, String email, String phone, String imageUrl) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        ImageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
