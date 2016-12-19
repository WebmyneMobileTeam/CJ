package com.androidapp.classifiedjobs.joblisting.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.ActivityJobListingBinding;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.fragment.ClassifeidJobsFragment;
import com.androidapp.classifiedjobs.joblisting.fragment.JobsFragment;

import java.util.ArrayList;
import java.util.List;

public class JobListingActivity extends AppCompatActivity {
    private ActivityJobListingBinding dataBind;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_job_listing);
        setSupportActionBar(dataBind.toolbar);
        init();
    }

    private void init() {
        initViewPager();
    }

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
                if (Prefs.with(JobListingActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
                    tab1.setText(R.string.job_en);
                    tab2.setText(R.string.classified_job_en);
                } else {
                    tab1.setText(R.string.job_am);
                    tab2.setText(R.string.classified_job_am);
                }
            }
        }
    }

    private void setupViewPager() {
        // init adapter for viewPager and assign fragment
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new JobsFragment(), getString(R.string.login_en));
        adapter.addFragment(new ClassifeidJobsFragment(), getString(R.string.register_en));
        dataBind.viewPager.setAdapter(adapter);
        dataBind.viewPager.setOffscreenPageLimit(0);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_category) {
            if (Prefs.with(JobListingActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
                Prefs.with(JobListingActivity.this).save(Constants.IS_LANG_ENG, false);
            } else {
                Prefs.with(JobListingActivity.this).save(Constants.IS_LANG_ENG, true);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
