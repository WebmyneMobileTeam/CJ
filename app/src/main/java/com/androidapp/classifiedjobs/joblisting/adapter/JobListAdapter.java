package com.androidapp.classifiedjobs.joblisting.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.JobItemBinding;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.joblisting.model.Job;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ishan on 12-12-2016.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.MyViewHolder> {
    private final Activity mActivity;
    private final List<Job> jobList;
    private JobItemBinding dataBind;

    public JobListAdapter(Activity mActivity, List<Job> jobList) {
        this.mActivity = mActivity;
        this.jobList = jobList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dataBind = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.job_item, parent, false);
        View itemView = dataBind.getRoot();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.jobTitle.setTypeface(Functions.getTF(mActivity));
        holder.viewJob.setTypeface(Functions.getTF(mActivity));
        holder.jobTitle.setText(jobList.get(position).getJobTitle().toString().trim());
        Glide.with(mActivity).load(jobList.get(position).getImg()).placeholder(R.drawable.image_filler).centerCrop().thumbnail(0.5f).into(holder.jobImg);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView jobImg;
        TextView jobTitle;
        Button viewJob;

        public MyViewHolder(View itemView) {
            super(itemView);
            jobImg = dataBind.jobImg;
            jobTitle = dataBind.jobTitle;
            viewJob=dataBind.viewJob;
        }
    }
}
