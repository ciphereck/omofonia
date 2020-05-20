package com.ciphereck.omofonia.model;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("admin")
    boolean isAdmin;
    @SerializedName("email")
    String email;
    @SerializedName("name")
    String name;
    @SerializedName("photo")
    String photo;

    public UserInfo(boolean isAdmin, String email, String name, String photo) {
        this.isAdmin = isAdmin;
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }
}
