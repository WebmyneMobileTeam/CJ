package com.androidapp.classifiedjobs.login.model;

import java.util.List;

/**
 * Created by ishan on 14-12-2016.
 */

public class RegistrationData {
    List<RCategoryList> CategoryList;
    RUserDetail UserDetail;

    public List<RCategoryList> getCategoryList() {
        return CategoryList;
    }

    public void setCategoryList(List<RCategoryList> categoryList) {
        CategoryList = categoryList;
    }

    public RUserDetail getUserDetail() {
        return UserDetail;
    }

    public void setUserDetail(RUserDetail userDetail) {
        UserDetail = userDetail;
    }
}
