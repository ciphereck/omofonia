package com.ciphereck.omofonia.model.election;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Candidate implements Serializable {
    @SerializedName("aadhaarNumber")
    String aadhaarNumber;
    @SerializedName("name")
    String name;
    @SerializedName("party")
    Party party;
    @SerializedName("picUrl")
    String picUrl;

    public Candidate(String aadhaarNumber, String name, Party party, String picUrl) {
        this.aadhaarNumber = aadhaarNumber;
        this.name = name;
        this.party = party;
        this.picUrl = picUrl;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public String getName() {
        return name;
    }

    public Party getParty() {
        return party;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
