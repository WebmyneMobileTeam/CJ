package com.androidapp.classifiedjobs.login.model;

/**
 * Created by ishan on 14-12-2016.
 */

public class LoginReq {
    String MobileNo;
    String Password;

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
