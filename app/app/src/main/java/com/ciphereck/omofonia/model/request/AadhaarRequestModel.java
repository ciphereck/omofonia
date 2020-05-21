package com.ciphereck.omofonia.model.request;

public class AadhaarRequestModel {
    final String aadhaarXml;
    final String password;
    final String aadhaarNumber;
    final String authHeader;

    public AadhaarRequestModel(String aadharXml, String password, String aadhaarNumber, String authHeader) {
        this.aadhaarXml = aadharXml;
        this.password = password;
        this.aadhaarNumber = aadhaarNumber;
        this.authHeader = authHeader;
    }
}
