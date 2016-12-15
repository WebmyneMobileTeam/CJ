package com.androidapp.classifiedjobs.api;

import com.androidapp.classifiedjobs.category.model.CategoryReq;
import com.androidapp.classifiedjobs.category.model.CategoryRes;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.login.model.LoginReq;
import com.androidapp.classifiedjobs.login.model.LoginRes;
import com.androidapp.classifiedjobs.login.model.RegistrationReq;
import com.androidapp.classifiedjobs.login.model.RegistrationRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ishan on 14-12-2016.
 */

public interface AppApi {

    //this api for registration
    @POST(Constants.REGISTRATION_URL)
    Call<RegistrationRes> registrationCall(@Body RegistrationReq registrationReq);

    //this api for login
    @POST(Constants.LOGIN_URL)
    Call<LoginRes> loginCall(@Body LoginReq loginReq);

    //this api for add category
    @POST(Constants.CATEGORY_URL)
    Call<CategoryRes> addCateogryCall(@Body CategoryReq categoryReq);


}
