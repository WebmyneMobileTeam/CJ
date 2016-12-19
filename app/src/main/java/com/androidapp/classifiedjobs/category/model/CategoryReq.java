package com.androidapp.classifiedjobs.category.model;

import java.util.List;

/**
 * Created by ishan on 14-12-2016.
 */

public class CategoryReq {
    List<Long> CategoryID;
    Long UserID;

    public List<Long> getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(List<Long> categoryID) {
        CategoryID = categoryID;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }
}
