package com.androidapp.classifiedjobs.joblisting.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.FragmentClassifiedJobsBinding;
import com.androidapp.classifiedjobs.joblisting.adapter.JobListAdapter;
import com.androidapp.classifiedjobs.joblisting.model.Job;

import java.util.ArrayList;

/**
 * Created by ishan on 12-12-2016.
 */

public class ClassifeidJobsFragment extends Fragment {
    private FragmentClassifiedJobsBinding dataBind;
    private ArrayList<Job> jobsList;
    private JobListAdapter mAdapter;

    public ClassifeidJobsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_classified_jobs, container, false);
        init();
        return dataBind.getRoot();
    }
    private void init() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        dataBind.cjobsList.setLayoutManager(mLayoutManager);
        dataBind.cjobsList.setItemAnimator(new DefaultItemAnimator());
        jobsList = new ArrayList<>();
        jobsList.add(new Job("image", "TITLE 1",R.drawable.img8));
        jobsList.add(new Job("image", "TITLE 2",R.drawable.img7));
        jobsList.add(new Job("image", "TITLE 3",R.drawable.img5));
        jobsList.add(new Job("image", "TITLE 4",R.drawable.img3));
        jobsList.add(new Job("image", "TITLE 5",R.drawable.img4));
        jobsList.add(new Job("image", "TITLE 6",R.drawable.img2));
        jobsList.add(new Job("image", "TITLE 7",R.drawable.img1));
        jobsList.add(new Job("image", "TITLE 8",R.drawable.img6));
        mAdapter = new JobListAdapter(getActivity(), jobsList);
        dataBind.cjobsList.setAdapter(mAdapter);
    }
}
