package com.androidapp.classifiedjobs.joblisting.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.classifiedjobs.CJMyApplication;
import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.api.call.GetJobList;
import com.androidapp.classifiedjobs.databinding.FragmentJobsBinding;
import com.androidapp.classifiedjobs.helper.ComplexPreferences;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.adapter.JobListAdapter;
import com.androidapp.classifiedjobs.joblisting.model.ClassifiedJobData;
import com.androidapp.classifiedjobs.joblisting.model.Job;
import com.androidapp.classifiedjobs.joblisting.model.JobData;
import com.androidapp.classifiedjobs.joblisting.model.JobReq;
import com.androidapp.classifiedjobs.login.model.RUserDetail;
import com.androidapp.classifiedjobs.login.model.UserData;
import com.androidapp.classifiedjobs.widget.familiarrecyclerview.FamiliarRecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan on 12-12-2016.
 */

public class JobsFragment extends Fragment {
    private FragmentJobsBinding dataBind;
    private List<Job> jobDataList;
    private JobListAdapter mAdapter;
    private boolean isVisible = false;
    private RUserDetail userData;
    private View footerView;
    //this variable use for check there is more data available or not so we can hide load more view
    private boolean isMoredata = true;

    public JobsFragment() {
        jobDataList = new ArrayList<>();
    }

    public void setAdapter(List<Job> inputList) {
        mAdapter.setItems(inputList);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (dataBind != null) {
            if (isVisibleToUser) {
                JobReq request = new JobReq();
                if (Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true)) {
                    request.setLanguageType("E");
                } else {
                    request.setLanguageType("A");
                }
                request.setLastJobID(0);
                long uId = userData.UserID();
                request.setUserID((int) uId);
                getJobList(request, false);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("null 2", isVisible + "");
        if (isVisible) {
            if (dataBind != null) {
                JobReq request = new JobReq();
                if (Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true)) {
                    request.setLanguageType("E");
                } else {
                    request.setLanguageType("A");
                }
                request.setLastJobID(0);
                long uId = userData.UserID();
                request.setUserID((int) uId);
                getJobList(request, false);
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_jobs, container, false);
        init();
        return dataBind.getRoot();
    }

    private void init() {

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(getActivity(), Constants.USER_DATA, Context.MODE_PRIVATE);
        userData = complexPreferences.getObject(Constants.USER_OBJ, RUserDetail.class);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        dataBind.jobsList.setLayoutManager(mLayoutManager);
        dataBind.jobsList.setItemAnimator(new DefaultItemAnimator());

        jobDataList = Job.getAllJobList(getActivity());
        if (jobDataList != null && jobDataList.size() > 0) {
        } else {
            jobDataList = new ArrayList<>();
        }

        mAdapter = new JobListAdapter(getActivity(), jobDataList);
        dataBind.jobsList.setEmptyView(dataBind.emptyView);
        dataBind.emptyView.setContent(getActivity().getString(R.string.no_record_en));
        dataBind.jobsList.setAdapter(mAdapter);

        dataBind.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                JobReq request = new JobReq();
                if (Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true)) {
                    request.setLanguageType("E");
                } else {
                    request.setLanguageType("A");
                }
                request.setLastJobID(0);
                long uId = userData.UserID();
                request.setUserID((int) uId);
                getJobList(request, false);

            }
        });
        footerView = View.inflate(getActivity(), R.layout.load_more, null);
        footerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        dataBind.jobsList.setOnScrollListener(new FamiliarRecyclerViewOnScrollListener(mLayoutManager) {
            @Override
            public void onScrolledToTop() {

            }

            @Override
            public void onScrolledToBottom() {
                //this variable use for check there is more data available or not so we can hide load more view
                if (isMoredata) {
                    dataBind.jobsList.addFooterView(footerView);
                    if (jobDataList != null && jobDataList.size() > 0) {
                        JobReq request = new JobReq();
                        if (Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true)) {
                            request.setLanguageType("E");
                        } else {
                            request.setLanguageType("A");
                        }
                        long jobId = jobDataList.get(jobDataList.size() - 1).JobID();
                        request.setLastJobID((int) jobId);
                        long uId = userData.UserID();
                        request.setUserID((int) uId);
                        getJobList(request, true);
                    } else {
                        dataBind.jobsList.removeFooterView(footerView);
                    }
                }
            }
        });

    }

    private void getJobList(JobReq jobReq, boolean isLoadMore) {
        Functions.logError(getActivity(), isLoadMore + " " + CJMyApplication.getGson().toJson(jobReq).toString());
        new GetJobList(getActivity(), jobReq, false, new GetJobList.OnGetJobView() {
            @Override
            public void getJobs(List<JobData> jobData) {
                Log.e("data", "get " + isLoadMore);
                if (isLoadMore) {
                    dataBind.jobsList.removeFooterView(footerView);
                }
                if (jobData.size() > 0) {
                    if (isLoadMore) {
                        for (int i = 0; i < jobData.size(); i++) {
                            JobData tempJobData = jobData.get(i);
                            tempJobData.setEng(Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true));
                            Job.insertJobList(tempJobData);
                        }
                        setAdapter(Job.getAllJobList(getActivity()));
                    } else {
                        Job.deleteAllData();
                        for (int i = 0; i < jobData.size(); i++) {
                            JobData tempJobData = jobData.get(i);
                            tempJobData.setEng(Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true));
                            Job.insertJobList(tempJobData);
                        }
                        setAdapter(Job.getAllJobList(getActivity()));
                    }
                } else {
                    //this variable use for check there is more data available or not so we can hide load more view
                    isMoredata = false;
                }
            }

            @Override
            public void showProgress() {
                if (dataBind != null) {
                    Log.e("Call", "method");
                    if (!isLoadMore) {
                        dataBind.swipeRefresh.setRefreshing(true);
                    }
                }
            }

            @Override
            public void hideProgress() {
                if (dataBind != null)
                    if (!isLoadMore) {
                        dataBind.swipeRefresh.setRefreshing(false);
                    }
            }

            @Override
            public void getCJobs(List<ClassifiedJobData> data) {

            }
        });

    }
}
