package com.ciphereck.omofonia.model.request;

public class OtpRequestModel {
    final String mobileNumber;
    final String otp;
    final String authHeader;

    public OtpRequestModel(String mobileNumber, String otp, String authHeader) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.authHeader = authHeader;
    }
}
