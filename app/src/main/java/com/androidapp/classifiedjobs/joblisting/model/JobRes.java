package com.androidapp.classifiedjobs.joblisting.model;

import java.util.List;

/**
 * Created by ishan on 15-12-2016.
 */

public class JobRes {
    int ResponseCode;
    JobResData ResponseData;
    String ResponseMsg;

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public JobResData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(JobResData responseData) {
        ResponseData = responseData;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }
}
