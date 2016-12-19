package com.androidapp.classifiedjobs.api.call;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.androidapp.classifiedjobs.CJMyApplication;
import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.api.AppApi;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.joblisting.model.ClassifiedJobData;
import com.androidapp.classifiedjobs.joblisting.model.ClassifiedJobRes;
import com.androidapp.classifiedjobs.joblisting.model.JobData;
import com.androidapp.classifiedjobs.joblisting.model.JobReq;
import com.androidapp.classifiedjobs.joblisting.model.JobRes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ishan on 15-12-2016.
 */

public class GetJobList {

    private Context context;
    private JobReq req;
    private OnGetJobView OnGetJobView;

    public GetJobList(Context context, JobReq req, boolean isClassified, OnGetJobView OnGetJobView) {
        this.context = context;
        this.req = req;
        this.OnGetJobView = OnGetJobView;
        if (isClassified) {
            initCJob();
        } else {
            initJob();
        }
    }

    private void initCJob() {
        // show progress
        if (OnGetJobView != null) {
            OnGetJobView.showProgress();
        }

        AppApi appApi = CJMyApplication.getRetrofit().create(AppApi.class);
        Call<ClassifiedJobRes> call = appApi.getClassifiedJobList(req);
        call.enqueue(new Callback<ClassifiedJobRes>() {
            @Override
            public void onResponse(Call<ClassifiedJobRes> call, Response<ClassifiedJobRes> response) {
                if (OnGetJobView != null) {
                    OnGetJobView.hideProgress();
                }

                if (response.isSuccessful() && response.body() != null) {
                    ClassifiedJobRes newRes = response.body();
                    if (newRes.getResponseCode() == 1) {
                        if (OnGetJobView != null) {
                            OnGetJobView.getCJobs(newRes.getResponseData().getData());
                        }
                    } else {
                        if (OnGetJobView != null) {
                            OnGetJobView.getJobs(new ArrayList<>());
                        }
                        Functions.logError((Activity) context, newRes.getResponseMsg());
                    }
                } else {
                    if (OnGetJobView != null) {
                        OnGetJobView.getJobs(new ArrayList<>());
                    }
                    Functions.logError((Activity) context, context.getString(R.string.wrong_response_en));
                }
            }

            @Override
            public void onFailure(Call<ClassifiedJobRes> call, Throwable t) {
                if (OnGetJobView != null) {
                    OnGetJobView.getJobs(new ArrayList<>());
                }
                if (OnGetJobView != null) {
                    OnGetJobView.hideProgress();
                }
            }
        });

    }

    private void initJob() {

        // show progress
        if (OnGetJobView != null) {
            OnGetJobView.showProgress();
        }

        AppApi appApi = CJMyApplication.getRetrofit().create(AppApi.class);
        Call<JobRes> call = appApi.getJobList(req);
        call.enqueue(new Callback<JobRes>() {
            @Override
            public void onResponse(Call<JobRes> call, Response<JobRes> response) {
                if (OnGetJobView != null) {
                    OnGetJobView.hideProgress();
                }

                if (response.isSuccessful() && response.body() != null) {
                    JobRes newRes = response.body();
                    if (newRes.getResponseCode() == 1) {
                        if (OnGetJobView != null) {
                            OnGetJobView.getJobs(newRes.getResponseData().getData());
                        }
                    } else {
                        if (OnGetJobView != null) {
                            OnGetJobView.getJobs(new ArrayList<>());
                        }
                        Functions.logError((Activity) context, newRes.getResponseMsg());
                    }
                } else {
                    if (OnGetJobView != null) {
                        OnGetJobView.getJobs(new ArrayList<>());
                    }
                    Functions.logError((Activity) context, context.getString(R.string.wrong_response_en));
                }
            }

            @Override
            public void onFailure(Call<JobRes> call, Throwable t) {
                if (OnGetJobView != null) {
                    OnGetJobView.getJobs(new ArrayList<>());
                }
                if (OnGetJobView != null) {
                    OnGetJobView.hideProgress();
                }
            }
        });


    }

    public interface OnGetJobView {

        void getJobs(List<JobData> jobData);

        void showProgress();

        void hideProgress();

        void getCJobs(List<ClassifiedJobData> data);
    }
}
