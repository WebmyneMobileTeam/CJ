package com.androidapp.classifiedjobs.login.model;

/**
 * Created by ishan on 14-12-2016.
 */

public class LoginRes {
    int ResponseCode;
    LResponseData ResponseData;
    String ResponseMsg;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public LResponseData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(LResponseData responseData) {
        ResponseData = responseData;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }
}
