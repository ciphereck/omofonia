package com.ciphereck.omofonia.model.election;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Party implements Serializable {
    @SerializedName("name")
    String name;
    @SerializedName("partySign")
    String partySign;
    @SerializedName("partyUrl")
    String partyUrl;

    public Party(String name, String partySign, String partyUrl) {
        this.name = name;
        this.partySign = partySign;
        this.partyUrl = partyUrl;
    }

    public String getName() {
        return name;
    }

    public String getPartySign() {
        return partySign;
    }

    public String getPartyUrl() {
        return partyUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartySign(String partySign) {
        this.partySign = partySign;
    }

    public void setPartyUrl(String partyUrl) {
        this.partyUrl = partyUrl;
    }
}
