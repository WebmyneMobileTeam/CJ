package com.androidapp.classifiedjobs.login.model;

import java.util.List;

/**
 * Created by ishan on 14-12-2016.
 */

public class RResponseData {
    List<RegistrationData> Data;

    public List<RegistrationData> getData() {
        return Data;
    }

    public void setData(List<RegistrationData> data) {
        Data = data;
    }
}
