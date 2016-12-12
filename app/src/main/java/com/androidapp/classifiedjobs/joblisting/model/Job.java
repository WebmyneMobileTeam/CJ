package com.androidapp.classifiedjobs.joblisting.model;

/**
 * Created by ishan on 12-12-2016.
 */

public class Job {
    String jobImgUrl;
    String jobTitle;
    int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Job(String jobImgUrl, String jobTitle, int img) {

        this.jobImgUrl = jobImgUrl;
        this.jobTitle = jobTitle;
        this.img = img;
    }

    public String getJobImgUrl() {
        return jobImgUrl;
    }

    public Job() {
    }

    public Job(String jobImgUrl, String jobTitle) {

        this.jobImgUrl = jobImgUrl;
        this.jobTitle = jobTitle;
    }

    public void setJobImgUrl(String jobImgUrl) {
        this.jobImgUrl = jobImgUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
