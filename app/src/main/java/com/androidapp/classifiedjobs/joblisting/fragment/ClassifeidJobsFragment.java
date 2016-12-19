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
import com.androidapp.classifiedjobs.databinding.FragmentClassifiedJobsBinding;
import com.androidapp.classifiedjobs.helper.ComplexPreferences;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.adapter.ClassifiedJobAdapter;
import com.androidapp.classifiedjobs.joblisting.adapter.JobListAdapter;
import com.androidapp.classifiedjobs.joblisting.model.ClassifiedJob;
import com.androidapp.classifiedjobs.joblisting.model.ClassifiedJob;
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

public class ClassifeidJobsFragment extends Fragment {
    private FragmentClassifiedJobsBinding dataBind;
    private List<ClassifiedJob> classifiedJobDataList;
    private ClassifiedJobAdapter mAdapter;
    private boolean isVisible = false;
    private RUserDetail userData;
    private View footerView;
    //this variable use for check there is more data available or not so we can hide load more view
    private boolean isMoredata = true;

    public ClassifeidJobsFragment() {
        classifiedJobDataList = new ArrayList<>();
    }

    public void setAdapter(List<ClassifiedJob> inputList) {
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
        if (isVisible) {
            Log.e("null 4", isVisible + "");
            if (dataBind != null) {
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_classified_jobs, container, false);
        init();
        return dataBind.getRoot();
    }

    private void init() {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(getActivity(), Constants.USER_DATA, Context.MODE_PRIVATE);
        userData = complexPreferences.getObject(Constants.USER_OBJ, RUserDetail.class);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        dataBind.cjobsList.setLayoutManager(mLayoutManager);
        dataBind.cjobsList.setItemAnimator(new DefaultItemAnimator());

        classifiedJobDataList = ClassifiedJob.getAllClassifiedJobList(getActivity());
        if (classifiedJobDataList != null && classifiedJobDataList.size() > 0) {
        } else {
            classifiedJobDataList = new ArrayList<>();
        }

        mAdapter = new ClassifiedJobAdapter(getActivity(), classifiedJobDataList);
        dataBind.cjobsList.setEmptyView(dataBind.emptyView);
        dataBind.cjobsList.setAdapter(mAdapter);

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

        dataBind.cjobsList.setOnScrollListener(new FamiliarRecyclerViewOnScrollListener(mLayoutManager) {
            @Override
            public void onScrolledToTop() {

            }

            @Override
            public void onScrolledToBottom() {
                //this variable use for check there is more data available or not so we can hide load more view
                if (isMoredata) {
                    dataBind.cjobsList.addFooterView(footerView);

                    if (classifiedJobDataList != null && classifiedJobDataList.size() > 0) {
                        JobReq request = new JobReq();
                        if (Prefs.with(getActivity()).getBoolean(Constants.IS_LANG_ENG, true)) {
                            request.setLanguageType("E");
                        } else {
                            request.setLanguageType("A");
                        }
                        long jobId = classifiedJobDataList.get(classifiedJobDataList.size() - 1).ClassifiedJobID();
                        request.setLastJobID((int) jobId);
                        long uId = userData.UserID();
                        request.setUserID((int) uId);
                        getJobList(request, true);
                    } else {
                        dataBind.cjobsList.removeFooterView(footerView);
                    }
                }
            }
        });

    }

    private void getJobList(JobReq jobReq, boolean isLoadMore) {
        Functions.logError(getActivity(), isLoadMore + " " + CJMyApplication.getGson().toJson(jobReq).toString());
        new GetJobList(getActivity(), jobReq, true, new GetJobList.OnGetJobView() {
            @Override
            public void getJobs(List<JobData> jobData) {
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
                if(isLoadMore){
                    dataBind.cjobsList.removeFooterView(footerView);}
                if (data.size() > 0) {
                    if (isLoadMore) {
                        for (int i = 0; i < data.size(); i++) {
                            ClassifiedJob.insertClassifiedJobList(data.get(i));
                        }
                        setAdapter(ClassifiedJob.getAllClassifiedJobList(getActivity()));
                    } else {
                        ClassifiedJob.deleteAllData();
                        for (int i = 0; i < data.size(); i++) {
                            ClassifiedJob.insertClassifiedJobList(data.get(i));
                        }
                        setAdapter(ClassifiedJob.getAllClassifiedJobList(getActivity()));
                    }
                } else {
                    //this variable use for check there is more data available or not so we can hide load more view
                    isMoredata = false;
                }

            }

        });

    }
}

