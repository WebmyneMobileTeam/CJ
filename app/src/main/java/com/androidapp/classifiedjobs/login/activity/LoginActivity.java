package com.androidapp.classifiedjobs.login.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.helper.AdvancedSpannableString;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;

public class LoginActivity extends AppCompatActivity {

//    private LoginActivityRevised dataBind;
    //    private ActivityLoginBinding dataBind;
    private AdvancedSpannableString advancedSpannableString;
    private View.OnClickListener clickListener;
    private boolean login = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_login);
        //set 0 position every time when app start
        Prefs.with(LoginActivity.this).save(Constants.CP_LOGIN, 0);


//        setTypeface();


//        init();

//        dataBind.segmented2.check(R.id.loginRB);
//        SegmentedGroup segmentedGroup = (SegmentedGroup) findViewById(R.id.segmented2);
//        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i) {
//                    case R.id.loginRB:
//                        login = true;
//                        init();
//                        break;
//                    case R.id.registerRB:
//                        login = false;
//                        init();
//                        break;
//                }
//            }
//        });

    }

/*    private void setTypeface() {
        dataBind.inputName.setTypeface(Functions.getTF(this));
        dataBind.inputEmail.setTypeface(Functions.getTF(this));
        dataBind.inputPhone.setTypeface(Functions.getTF(this));
        dataBind.inputPassword.setTypeface(Functions.getTF(this));
        dataBind.engBtn.setTypeface(Functions.getTF(this));
        dataBind.amhBtn.setTypeface(Functions.getTF(this));
        dataBind.loginBtn.setTypeface(Functions.getTF(this));
        dataBind.loginRB.setTypeface(Functions.getTF(this));
        dataBind.registerRB.setTypeface(Functions.getTF(this));
    }

    private void init() {

        //set labels according to lang selection
        if (Prefs.with(LoginActivity.this).getBoolean(Constants.LANG, true)) {
            dataBind.inputName.setHint(getResources().getString(R.string.enter_your_name_en));
            dataBind.inputEmail.setHint(getResources().getString(R.string.enter_your_email_en));
            dataBind.inputPhone.setHint(getResources().getString(R.string.enter_your_phone_en));
            dataBind.inputPassword.setHint(getResources().getString(R.string.enter_your_password_en));
            ((RadioButton) findViewById(R.id.loginRB)).setText(R.string.login_en);
            ((RadioButton) findViewById(R.id.registerRB)).setText(R.string.register_en);
            if (login) {
                dataBind.inputName.setVisibility(View.GONE);
                dataBind.inputEmail.setVisibility(View.GONE);
                dataBind.loginBtn.setText(R.string.login_en);
            } else {
                dataBind.inputName.setVisibility(View.VISIBLE);
                dataBind.inputEmail.setVisibility(View.VISIBLE);
                dataBind.loginBtn.setText(R.string.register_en);
            }
        } else {
            dataBind.inputName.setHint(getResources().getString(R.string.enter_your_name_am));
            dataBind.inputEmail.setHint(getResources().getString(R.string.enter_your_email_am));
            dataBind.inputPhone.setHint(getResources().getString(R.string.enter_your_phone_am));
            dataBind.inputPassword.setHint(getResources().getString(R.string.enter_your_password_am));
            ((RadioButton) findViewById(R.id.loginRB)).setText(R.string.login_am);
            ((RadioButton) findViewById(R.id.registerRB)).setText(R.string.register_am);
            if (login) {
                dataBind.inputName.setVisibility(View.GONE);
                dataBind.inputEmail.setVisibility(View.GONE);
                dataBind.loginBtn.setText(R.string.login_am);
            } else {
                dataBind.inputName.setVisibility(View.VISIBLE);
                dataBind.inputEmail.setVisibility(View.VISIBLE);
                dataBind.loginBtn.setText(R.string.register_am);
            }
        }


        //set activity_login_revised title
        initLoginTitle();

        //init Lang Button
        initLangBtn();

        //click listener for change lang
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Prefs.with(LoginActivity.this).getBoolean(Constants.LANG, true)) {
                    Prefs.with(LoginActivity.this).save(Constants.LANG, false);
                } else {
                    Prefs.with(LoginActivity.this).save(Constants.LANG, true);
                }
                //fun for change button's ui
                initLangBtn();
                //call init() for refresh ui
                init();
            }
        };

        dataBind.engBtn.setOnClickListener(clickListener);
        dataBind.amhBtn.setOnClickListener(clickListener);

        //listener for password hide or show fun
        clickListener();


        dataBind.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Functions.fireIntent(LoginActivity.this, JobListingActivity.class, true);
                    finish();
                }
            }
        });

    }

    private boolean validate() {
        if (login) {

        }
        return true;
    }

    private void initLangBtn() {
        if (Prefs.with(LoginActivity.this).getBoolean(Constants.LANG, true)) {
            dataBind.engBtn.setBackgroundResource(R.drawable.blue_circle);
            dataBind.amhBtn.setBackgroundResource(R.drawable.black_circle);
        } else {
            dataBind.amhBtn.setBackgroundResource(R.drawable.blue_circle);
            dataBind.engBtn.setBackgroundResource(R.drawable.black_circle);
        }
    }

    private void initLoginTitle() {
        //use advanceSpannableString for bold partially text
        if (Prefs.with(LoginActivity.this).getBoolean(Constants.LANG, true)) {
            advancedSpannableString = new AdvancedSpannableString(getString(R.string.choose_lang_en));
            advancedSpannableString.setBold(getString(R.string.lang_txt_en));

            //set activity_login_revised title
            dataBind.loginTitle.setText(advancedSpannableString);
            //set type face
            dataBind.loginTitle.setTypeface(Functions.getTF(this));

        } else {
            advancedSpannableString = new AdvancedSpannableString(getString(R.string.choose_lang_am));
            advancedSpannableString.setBold(getString(R.string.lang_txt_am));

            //set activity_login_revised title
            dataBind.loginTitle.setText(advancedSpannableString);
        }
    }
*//*
    private void initViewPager() {
        //set view pager....
        setupViewPager();

        dataBind.tabLayout.setupWithViewPager(dataBind.viewPager);

        setTabText();
    }

    private void setTabText() {
        if (dataBind.tabLayout.getTabCount() == 2) {
            TabLayout.Tab tab1 = dataBind.tabLayout.getTabAt(0);
            TabLayout.Tab tab2 = dataBind.tabLayout.getTabAt(1);
            if (tab1 != null && tab2 != null) {
                if (Prefs.with(LoginActivity.this).getBoolean(Constants.LANG, true)) {
                    tab1.setText(R.string.login_en);
                    tab2.setText(R.string.register_en);
                } else {
                    tab1.setText(R.string.login_am);
                    tab2.setText(R.string.register_am);
                }
            }
        }
    }


    private void setupViewPager() {
        // init adapter for viewPager and assign fragment
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), getString(R.string.login_en));
        adapter.addFragment(new RegisterFragment(), getString(R.string.register_en));
        dataBind.viewPager.setAdapter(adapter);

        dataBind.viewPager.setCurrentItem(Prefs.with(LoginActivity.this).getInt(Constants.CP_LOGIN, 0));
        dataBind.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Prefs.with(LoginActivity.this).save(Constants.CP_LOGIN, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*//*

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
    }*/
}
