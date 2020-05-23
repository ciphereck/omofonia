package com.ciphereck.omofonia.model.response;

import com.ciphereck.omofonia.model.election.Election;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElectionResponseModel {
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Election> electionList;

    public ElectionResponseModel(boolean success, String message, List<Election> electionList) {
        this.success = success;
        this.message = message;
        this.electionList = electionList;
    }

    public ElectionResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Election> getElectionList() {
        return electionList;
    }
}
