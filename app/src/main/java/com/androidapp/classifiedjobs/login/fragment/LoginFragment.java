package com.androidapp.classifiedjobs.login.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.FragmentLoginBinding;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;

/**
 * Created by ishan on 09-12-2016.
 */

public class LoginFragment extends Fragment {
    private FragmentLoginBinding dataBind;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        init();
        return dataBind.getRoot();
    }

    private void init() {
        if (Prefs.with(getActivity()).getBoolean(Constants.LANG, true)) {
            dataBind.enterPhone.setHint(getActivity().getResources().getString(R.string.enter_your_phone_en));
            dataBind.enterPassword.setHint(getActivity().getResources().getString(R.string.enter_your_password_en));
            dataBind.loginBtn.setText(R.string.login_en);
        }else{
            dataBind.enterPhone.setHint(getActivity().getResources().getString(R.string.enter_your_phone_am));
            dataBind.enterPassword.setHint(getActivity().getResources().getString(R.string.enter_your_password_am));
            dataBind.loginBtn.setText(R.string.login_am);
        }
        clickListener();
    }

    private void clickListener() {
        dataBind.inputPassword.setOnTouchListener(new View.OnTouchListener() {
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
                        dataBind.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // show password
                        dataBind.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                    }
                }

                return false;
            }
        });
    }
}
