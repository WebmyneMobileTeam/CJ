package com.androidapp.classifiedjobs.joblisting.model;

/**
 * Created by ishan on 15-12-2016.
 */

public class ClassifiedJobRes {
    int ResponseCode;
    ClassifiedJobResData ResponseData;
    String ResponseMsg;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public ClassifiedJobResData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(ClassifiedJobResData responseData) {
        ResponseData = responseData;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }
}
