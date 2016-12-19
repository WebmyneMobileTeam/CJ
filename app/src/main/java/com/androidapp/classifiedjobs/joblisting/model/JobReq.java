package com.androidapp.classifiedjobs.joblisting.model;

/**
 * Created by ishan on 15-12-2016.
 */

public class JobReq {
    String LanguageType;
    int LastJobID;
    int UserID;

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public int getLastJobID() {
        return LastJobID;
    }

    public void setLastJobID(int lastJobID) {
        LastJobID = lastJobID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
