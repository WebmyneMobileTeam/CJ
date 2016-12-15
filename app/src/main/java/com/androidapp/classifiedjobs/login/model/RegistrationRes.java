package com.androidapp.classifiedjobs.login.model;

/**
 * Created by ishan on 14-12-2016.
 */

public class RegistrationRes {
    int ResponseCode;
    RResponseData ResponseData;
    String ResponseMsg;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public RResponseData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(RResponseData responseData) {
        ResponseData = responseData;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }
}
