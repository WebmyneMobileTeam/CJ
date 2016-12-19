package com.androidapp.classifiedjobs.helper;

/**
 * Created by ishan on 09-12-2016.
 */

public class Constants {
    //current page of activity_login_revised or register
    public static final String CP_LOGIN = "cp_login";
    public static final String FCM_TOKEN = "fcm_token";
    public static final String BASE_URL = "http://ws-srv-net.in.webmyne.com/Applications/ClassifiedJOB/WCF/Services/";

    public static final String LOGIN_URL = "Account.svc/json/Login";
    public static final String REGISTRATION_URL = "Account.svc/json/Registration";
    public static final String CATEGORY_URL = "Users.svc/json/AddUserCategory";
    public static final String UPDATE_USER_DATA_URL = "Users.svc/json/UpdateUserDetail";

    public static final String JOB_LIST = "Job.svc/json/GeneralJobList";
    public static final String CLASSIFIED_JOB_LIST = "Job.svc/json/ClassifiedJobList";

    public static final String USER_DATA = "user_data";
    public static final String IS_LOGIN = "is_login";
    public static final String USER_OBJ = "user";
    public static final String CATEGORY_SELECTED = "category_selected";
    //for lang selection
    public static String IS_LANG_ENG = "lang";
}
