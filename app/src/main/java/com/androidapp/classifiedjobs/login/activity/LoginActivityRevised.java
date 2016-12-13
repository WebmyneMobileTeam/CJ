package com.androidapp.classifiedjobs.login.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.ActivityLoginBinding;
import com.androidapp.classifiedjobs.databinding.ActivityLoginRevisedBinding;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.activity.JobListingActivity;

/**
 * Created by ishan on 12-12-2016.
 */

public class LoginActivityRevised extends AppCompatActivity {
    private ActivityLoginRevisedBinding dataBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_login_revised);


        if (Prefs.with(this).getBoolean(Constants.LANG, true)) {
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

        dataBind.langSegment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (dataBind.engRB.isChecked()) {
                    Prefs.with(LoginActivityRevised.this).save(Constants.LANG, true);
                } else {
                    Prefs.with(LoginActivityRevised.this).save(Constants.LANG, false);
                }
                if (dataBind.amhRB.isChecked()) {
                    Prefs.with(LoginActivityRevised.this).save(Constants.LANG, false);
                } else {
                    Prefs.with(LoginActivityRevised.this).save(Constants.LANG, true);
                }
                init();
            }
        });


        //set labels according to lang selection
        if (Prefs.with(LoginActivityRevised.this).getBoolean(Constants.LANG, true)) {
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
                Functions.fireIntent(LoginActivityRevised.this, JobListingActivity.class, true);
            }
        });

        dataBind.llSigninContent.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.fireIntent(LoginActivityRevised.this, JobListingActivity.class, true);
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
