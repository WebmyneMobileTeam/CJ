package com.androidapp.classifiedjobs.login.model;

import java.util.List;

/**
 * Created by ishan on 14-12-2016.
 */

public class LResponseData {
    List<RUserDetail> Data;

    public List<RUserDetail> getData() {
        return Data;
    }

    public void setData(List<RUserDetail> data) {
        Data = data;
    }
}
