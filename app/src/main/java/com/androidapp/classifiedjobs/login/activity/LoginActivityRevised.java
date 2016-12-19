package com.androidapp.classifiedjobs.login.activity;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidapp.classifiedjobs.CJMyApplication;
import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.api.AppApi;
import com.androidapp.classifiedjobs.category.CategorySelectionActivity;
import com.androidapp.classifiedjobs.helper.RetrofitErrorHelper;
import com.androidapp.classifiedjobs.login.model.CategoryList;
import com.androidapp.classifiedjobs.databinding.ActivityLoginRevisedBinding;
import com.androidapp.classifiedjobs.helper.ComplexPreferences;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.activity.JobListingActivity;
import com.androidapp.classifiedjobs.login.model.LoginReq;
import com.androidapp.classifiedjobs.login.model.LoginRes;
import com.androidapp.classifiedjobs.login.model.RUserDetail;
import com.androidapp.classifiedjobs.login.model.RegistrationReq;
import com.androidapp.classifiedjobs.login.model.RegistrationRes;
import com.androidapp.classifiedjobs.login.model.UserData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jaredrummler.android.device.DeviceName;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ishan on 12-12-2016.
 */

public class LoginActivityRevised extends AppCompatActivity {
    private ActivityLoginRevisedBinding dataBind;
    private long mLastClickTime;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_login_revised);


        if (Prefs.with(this).getBoolean(Constants.IS_LANG_ENG, true)) {
            dataBind.engRB.setChecked(true);
            dataBind.amhRB.setChecked(false);
        } else {
            dataBind.engRB.setChecked(false);
            dataBind.amhRB.setChecked(true);
        }

        //set custom font
        setTypeface();

        //initially set eng lang
        dataBind.engRB.setChecked(true);

        //initially open activity_login_revised page
        showSigninForm();

        init();


    }

    private void init() {

        Functions.logError(LoginActivityRevised.this, FirebaseInstanceId.getInstance().getToken());

        dialog = new SpotsDialog(this, R.style.Custom);

        if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true)) {
            dataBind.langSegment.check(R.id.engRB);
        } else {
            dataBind.langSegment.check(R.id.amhRB);
        }

        dataBind.langSegment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (dataBind.engRB.isChecked()) {
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LANG_ENG, true);
                } else {
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LANG_ENG, false);
                }
                if (dataBind.amhRB.isChecked()) {
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LANG_ENG, false);
                } else {
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LANG_ENG, true);
                }
                init();
            }
        });


        TextView txtChooseLanguage = (TextView) findViewById(R.id.txtChooseLanguage);

        //set labels according to lang selection
        if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true)) {
            dataBind.tvSigninInvoker.setText(R.string.login_en);
            dataBind.tvSignupInvoker.setText(R.string.register_en);

            dataBind.llSigninContent.btnSignin.setText(R.string.login_en);
            dataBind.llSignupContent.btnSignup.setText(R.string.register_en);

            dataBind.llSignupContent.enterName.setHint(getResources().getString(R.string.enter_your_name_en));
            dataBind.llSignupContent.enterEmail.setHint(getResources().getString(R.string.enter_your_email_en));
            dataBind.llSignupContent.enterPhone.setHint(getResources().getString(R.string.enter_your_phone_en));
            dataBind.llSignupContent.enterPassword.setHint(getResources().getString(R.string.enter_your_password_en));

            dataBind.llSigninContent.enterPhoneET.setHint(getResources().getString(R.string.enter_your_phone_en));
            dataBind.llSigninContent.enterPasswordET.setHint(getResources().getString(R.string.enter_your_password_en));

            txtChooseLanguage.setText(R.string.choose_lang_en);


        } else {
            dataBind.tvSigninInvoker.setText(R.string.login_am);
            dataBind.tvSignupInvoker.setText(R.string.register_am);

            dataBind.llSigninContent.btnSignin.setText(R.string.login_am);
            dataBind.llSignupContent.btnSignup.setText(R.string.register_am);

            dataBind.llSignupContent.enterName.setHint(getResources().getString(R.string.enter_your_name_am));
            dataBind.llSignupContent.enterEmail.setHint(getResources().getString(R.string.enter_your_email_am));
            dataBind.llSignupContent.enterPhone.setHint(getResources().getString(R.string.enter_your_phone_am));
            dataBind.llSignupContent.enterPassword.setHint(getResources().getString(R.string.enter_your_password_am));

            dataBind.llSigninContent.enterPhoneET.setHint(getResources().getString(R.string.enter_your_phone_am));
            dataBind.llSigninContent.enterPasswordET.setHint(getResources().getString(R.string.enter_your_password_am));

            txtChooseLanguage.setText(R.string.choose_lang_am);
        }

        //open register page
        dataBind.tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignupForm();
            }
        });

        //open activity_login_revised page
        dataBind.tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSigninForm();
            }
        });


        dataBind.llSignupContent.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (validationRegistration(view)) {
                    registration();
                }
            }
        });

        dataBind.llSigninContent.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (validationLogin(view)) {
                    login();
                }
            }
        });

        dataBind.llSigninContent.password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                        return false;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    if (validationLogin(view)) {
                        login();
                    }
                }
                return false;
            }
        });
        dataBind.llSignupContent.inputPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
                        return false;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    if (validationRegistration(view)) {
                        registration();
                    }
                }
                return false;
            }
        });

        //hide or show fun on password
        listenerOnPassword();
    }

    //this method for hide or show fun on password
    private void listenerOnPassword() {
        dataBind.llSignupContent.inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                EditText editText = (EditText) view;
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // hide password
                        dataBind.llSignupContent.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // show password
                        dataBind.llSignupContent.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                    }
                }

                return false;
            }
        });

        dataBind.llSigninContent.password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                EditText editText = (EditText) view;
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // hide password
                        dataBind.llSigninContent.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // show password
                        dataBind.llSigninContent.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                    }
                }

                return false;
            }
        });
    }

    //this method for validate login field
    private boolean validationLogin(View view) {
        if (!Functions.isInternetConnected(LoginActivityRevised.this)) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.internet_error_en));
            else
                Functions.showSnack(view, getString(R.string.internet_error_am));
            return false;
        } else if (dataBind.llSigninContent.phone.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_phone_no_en));
            else
                Functions.showSnack(view, getString(R.string.enter_phone_no_am));
            return false;
        } else if (dataBind.llSigninContent.phone.getText().toString().trim().length() != 10) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_valid_phone_en));
            else
                Functions.showSnack(view, getString(R.string.enter_valid_phone_am));
            return false;
        } else if (dataBind.llSigninContent.password.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_password_en));
            else
                Functions.showSnack(view, getString(R.string.enter_password_am));
            return false;
        }

        return true;
    }

    //this method for validate registration field
    private boolean validationRegistration(View view) {
        if (!Functions.isInternetConnected(LoginActivityRevised.this)) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.internet_error_en));
            else
                Functions.showSnack(view, getString(R.string.internet_error_am));
            return false;
        } else if (dataBind.llSignupContent.inputName.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_name_en));
            else
                Functions.showSnack(view, getString(R.string.enter_name_am));
            return false;
        } else if (dataBind.llSignupContent.inputEmail.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_email_id_en));
            else
                Functions.showSnack(view, getString(R.string.enter_email_id_am));
            return false;
        } else if (!dataBind.llSignupContent.inputEmail.getText().toString().trim().matches(emailPattern)) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_valid_email_en));
            else
                Functions.showSnack(view, getString(R.string.enter_valid_email_am));
            return false;
        } else if (dataBind.llSignupContent.inputPhone.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_phone_no_en));
            else
                Functions.showSnack(view, getString(R.string.enter_phone_no_am));
            return false;
        } else if (dataBind.llSignupContent.inputPhone.getText().toString().trim().length() != 10) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_valid_phone_en));
            else
                Functions.showSnack(view, getString(R.string.enter_valid_phone_am));
            return false;
        } else if (dataBind.llSignupContent.inputPassword.getText().toString().trim().equals("")) {
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(view, getString(R.string.enter_password_en));
            else
                Functions.showSnack(view, getString(R.string.enter_password_am));
            return false;
        }

        return true;
    }

    //in this method registration request obj will create and send to api
    private void registration() {
        String fcmToken = Prefs.with(this).getString(Constants.FCM_TOKEN, null);

        RegistrationReq registrationReq = new RegistrationReq();
        registrationReq.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        registrationReq.setDeviceModel(DeviceName.getDeviceName());
        if (fcmToken != null && !fcmToken.trim().equals("")) {
            registrationReq.setDeviceToken(fcmToken);
        } else {
            registrationReq.setDeviceToken(FirebaseInstanceId.getInstance().getToken());
        }
        registrationReq.setDeviceType("Android");
        registrationReq.setName(dataBind.llSignupContent.inputName.getText().toString().trim());
        registrationReq.setEmail(dataBind.llSignupContent.inputEmail.getText().toString().trim());
        registrationReq.setMobileNo(dataBind.llSignupContent.inputPhone.getText().toString().trim());
        registrationReq.setPassword(dataBind.llSignupContent.inputPassword.getText().toString().trim());
        if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true)) {
            registrationReq.setLanguageType("E");
        } else {
            registrationReq.setLanguageType("A");
        }

        if (registrationReq != null) {
            callApiForRegistration(registrationReq);
        }
    }

    //in this method registration api will call
    private void callApiForRegistration(RegistrationReq registrationReq) {
        if (dialog != null) {
            dialog.show();
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                dialog.setTitle(getString(R.string.authentication_en));
            else
                dialog.setTitle(getString(R.string.authentication_am));
        }
        Functions.logError(this, CJMyApplication.getGson().toJson(registrationReq).toString());
        AppApi appApi = CJMyApplication.getRetrofit().create(AppApi.class);
        Call<RegistrationRes> call = appApi.registrationCall(registrationReq);
        call.enqueue(new Callback<RegistrationRes>() {
            @Override
            public void onResponse(Call<RegistrationRes> call, Response<RegistrationRes> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null && response.body().getResponseData() != null &&
                        response.body().getResponseData().getData() != null &&
                        response.body().getResponseData().getData().size() > 0 &&
                        response.body().getResponseData().getData().get(0).getUserDetail() != null) {
                    Functions.logError(LoginActivityRevised.this, response.code() + " " + CJMyApplication.getGson().toJson(response.body()).toString());
                    UserData.insertUserData(response.body().getResponseData().getData().get(0).getUserDetail());
                    ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(LoginActivityRevised.this, Constants.USER_DATA, MODE_PRIVATE);
                    RUserDetail userData = response.body().getResponseData().getData().get(0).getUserDetail();
                    complexPreferences.putObject(Constants.USER_OBJ, userData);
                    complexPreferences.commit();
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LOGIN, true);
                    if (response.body().getResponseData().getData().get(0).getCategoryList() != null &&
                            response.body().getResponseData().getData().get(0).getCategoryList().size() > 0) {
                        for (int i = 0; i < response.body().getResponseData().getData().get(0).getCategoryList().size(); i++) {
                            CategoryList.insertCategoryList(response.body().getResponseData().getData().get(0).getCategoryList().get(i));
                        }
                    }

                    Functions.fireIntent(LoginActivityRevised.this, CategorySelectionActivity.class, true);
                    finish();
                } else {
                    if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_en));
                    else
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_am));
                }
            }

            @Override
            public void onFailure(Call<RegistrationRes> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                Functions.logError(LoginActivityRevised.this, t.getMessage());
                if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_en));
                else
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_am));
            }
        });
    }

    //in this method login request obj will create and send to api for validate
    private void login() {
        LoginReq loginReq = new LoginReq();
        loginReq.setMobileNo(dataBind.llSigninContent.phone.getText().toString().trim());
        loginReq.setPassword(dataBind.llSigninContent.password.getText().toString().trim());
        if (loginReq != null) {
            callApiForLogin(loginReq);
        }
    }

    //in this method login api will call
    private void callApiForLogin(LoginReq loginReq) {
        if (dialog != null) {
            dialog.show();
            if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                dialog.setTitle(getString(R.string.authentication_en));
            else
                dialog.setTitle(getString(R.string.authentication_am));
        }
        Functions.logError(this, CJMyApplication.getGson().toJson(loginReq).toString());
        AppApi appApi = CJMyApplication.getRetrofit().create(AppApi.class);
        appApi.loginCall(loginReq).enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (response.body() != null && response.body().getResponseData() != null &&
                        response.body().getResponseData().getData() != null &&
                        response.body().getResponseData().getData().size() > 0) {
                    Functions.logError(LoginActivityRevised.this, response.code() + " " + CJMyApplication.getGson().toJson(response.body()).toString());
                    UserData.insertUserData(response.body().getResponseData().getData().get(0));
                    ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(LoginActivityRevised.this, Constants.USER_DATA, MODE_PRIVATE);
                    RUserDetail userData = response.body().getResponseData().getData().get(0);
                    complexPreferences.putObject(Constants.USER_OBJ, userData);
                    complexPreferences.commit();
                    Prefs.with(LoginActivityRevised.this).save(Constants.IS_LOGIN, true);
                    if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.CATEGORY_SELECTED, false)) {
                        Functions.fireIntent(LoginActivityRevised.this, JobListingActivity.class, true);
                    } else {
                        Functions.fireIntent(LoginActivityRevised.this, CategorySelectionActivity.class, true);
                    }
                    finish();
                } else {
                    if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.try_to_register_en));
                    else
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.try_to_register_am));
                    showSignupForm();
                }
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.IS_LANG_ENG, true))
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_en));
                else
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_am));
            }
        });
    }

    private void setTypeface() {
        //register page
        dataBind.llSignupContent.inputName.setTypeface(Functions.getTF(this));
        dataBind.llSignupContent.inputEmail.setTypeface(Functions.getTF(this));
        dataBind.llSignupContent.inputPhone.setTypeface(Functions.getTF(this));
        dataBind.llSignupContent.inputPassword.setTypeface(Functions.getTF(this));
        dataBind.engRB.setTypeface(Functions.getTF(this));
        dataBind.amhRB.setTypeface(Functions.getTF(this));

        dataBind.tvSigninInvoker.setTypeface(Functions.getTF(this));
        dataBind.tvSignupInvoker.setTypeface(Functions.getTF(this));

        dataBind.llSignupContent.btnSignup.setTypeface(Functions.getTF(this));
        dataBind.llSigninContent.btnSignin.setTypeface(Functions.getTF(this));


        //login page
        dataBind.llSigninContent.phone.setTypeface(Functions.getTF(this));
        dataBind.llSigninContent.password.setTypeface(Functions.getTF(this));
    }

    private void showSignupForm() {

        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) dataBind.llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        dataBind.llSignin.requestLayout();

        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) dataBind.llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        dataBind.llSignup.requestLayout();

        dataBind.tvSignupInvoker.setVisibility(View.GONE);
        dataBind.tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right_to_left);
        dataBind.llSignup.startAnimation(translate);

        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
        //dataBind.llSignupContent.btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {

        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) dataBind.llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        dataBind.llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) dataBind.llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        dataBind.llSignup.requestLayout();


        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left_to_right);
        dataBind.llSignin.startAnimation(translate);

        dataBind.tvSignupInvoker.setVisibility(View.VISIBLE);
        dataBind.tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left_to_right);
        //dataBind.llSigninContent.btnSignin.startAnimation(clockwise);
    }
}
