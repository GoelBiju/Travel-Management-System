package com.example.customermobileapplication.BindingModels;

import com.google.gson.annotations.SerializedName;

public class LoginBindingModel {

    @SerializedName("emailAddress")
    public String emailAddress;

    @SerializedName("password")
    public String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
