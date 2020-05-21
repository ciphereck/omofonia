package com.ciphereck.omofonia.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("token")
    String token;
    @SerializedName("userInfo")
    UserInfo userInfo;

    public String getToken() {
        return token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
