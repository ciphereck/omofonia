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
    @SerializedName("aadhaarName")
    String aadhaarName;
    @SerializedName("address")
    String address;

    public UserInfo(boolean isAdmin, String email, String name, String photo, String aadhaarName, String address, String dob, String gender, String hashedMobileNumber, String password, String aadhaarPicJP2000, String aadhaarNumber, String mobileNumber) {
        this.isAdmin = isAdmin;
        this.email = email;
        this.name = name;
        this.photo = photo;
        this.aadhaarName = aadhaarName;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.hashedMobileNumber = hashedMobileNumber;
        this.password = password;
        this.aadhaarPicJP2000 = aadhaarPicJP2000;
        this.aadhaarNumber = aadhaarNumber;
        this.mobileNumber = mobileNumber;
    }

    public UserInfo(boolean isAdmin, String email, String name, String photo, String aadhaarName, String address, String dob, String gender, String hashedMobileNumber, String password, String aadhaarPicJP2000, String aadhaarNumber) {
        this.isAdmin = isAdmin;
        this.email = email;
        this.name = name;
        this.photo = photo;
        this.aadhaarName = aadhaarName;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.hashedMobileNumber = hashedMobileNumber;
        this.password = password;
        this.aadhaarPicJP2000 = aadhaarPicJP2000;
        this.aadhaarNumber = aadhaarNumber;
    }

    @SerializedName("dob")
    String dob;
    @SerializedName("gender")
    String gender;
    @SerializedName("hashedMobileNo")
    String hashedMobileNumber;
    @SerializedName("password")
    String password;
    @SerializedName("picInJP2000")
    String aadhaarPicJP2000;
    @SerializedName("aadhaarNumber")
    String aadhaarNumber;
    @SerializedName("mobileNumber")
    String mobileNumber;

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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

    public String getAadhaarName() {
        return aadhaarName;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getHashedMobileNumber() {
        return hashedMobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAadhaarPicJP2000() {
        return aadhaarPicJP2000;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
