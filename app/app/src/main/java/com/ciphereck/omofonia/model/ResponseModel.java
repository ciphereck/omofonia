package com.ciphereck.omofonia.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public ResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
