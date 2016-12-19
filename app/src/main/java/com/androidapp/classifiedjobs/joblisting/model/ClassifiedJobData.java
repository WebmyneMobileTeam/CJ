package com.androidapp.classifiedjobs.joblisting.model;

import android.support.annotation.Nullable;

/**
 * Created by ishan on 15-12-2016.
 */

public class ClassifiedJobData extends ClassifiedJob{

    private Long ClassifiedJobID;
    private String Description;
    private String EndDate;
    private String ImageName;
    private Long IsActive;
    private String JobTitle;
    private String JobType;
    private Long JobTypeID;
    private String LanguageType;
    private String StartDate;
    private boolean isEng;

    public Long getClassifiedJobID() {
        return ClassifiedJobID;
    }

    public void setClassifiedJobID(Long classifiedJobID) {
        ClassifiedJobID = classifiedJobID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public Long getIsActive() {
        return IsActive;
    }

    public void setIsActive(Long isActive) {
        IsActive = isActive;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public Long getJobTypeID() {
        return JobTypeID;
    }

    public void setJobTypeID(Long jobTypeID) {
        JobTypeID = jobTypeID;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEng(boolean eng) {
        isEng = eng;
    }

    @Nullable
    @Override
    public Long ClassifiedJobID() {
        return ClassifiedJobID;
    }

    @Nullable
    @Override
    public String Description() {
        return Description;
    }

    @Nullable
    @Override
    public String EndDate() {
        return EndDate;
    }

    @Nullable
    @Override
    public String ImageName() {
        return ImageName;
    }

    @Nullable
    @Override
    public Long IsActive() {
        return IsActive;
    }

    @Nullable
    @Override
    public String JobTitle() {
        return JobTitle;
    }

    @Nullable
    @Override
    public String JobType() {
        return JobType;
    }

    @Nullable
    @Override
    public Long JobTypeID() {
        return JobTypeID;
    }

    @Nullable
    @Override
    public String LanguageType() {
        return LanguageType;
    }

    @Nullable
    @Override
    public String StartDate() {
        return StartDate;
    }

    @Override
    public boolean isEng() {
        return isEng;
    }

    @Override
    public String toString() {
        return "ClassifiedJob{"
                + "ClassifiedJobID=" + ClassifiedJobID + ", "
                + "Description=" + Description + ", "
                + "EndDate=" + EndDate + ", "
                + "ImageName=" + ImageName + ", "
                + "IsActive=" + IsActive + ", "
                + "JobTitle=" + JobTitle + ", "
                + "JobType=" + JobType + ", "
                + "JobTypeID=" + JobTypeID + ", "
                + "LanguageType=" + LanguageType + ", "
                + "StartDate=" + StartDate + ", "
                + "isEng=" + isEng
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ClassifiedJob) {
            ClassifiedJob that = (ClassifiedJob) o;
            return ((this.ClassifiedJobID == null) ? (that.ClassifiedJobID() == null) : this.ClassifiedJobID.equals(that.ClassifiedJobID()))
                    && ((this.Description == null) ? (that.Description() == null) : this.Description.equals(that.Description()))
                    && ((this.EndDate == null) ? (that.EndDate() == null) : this.EndDate.equals(that.EndDate()))
                    && ((this.ImageName == null) ? (that.ImageName() == null) : this.ImageName.equals(that.ImageName()))
                    && ((this.IsActive == null) ? (that.IsActive() == null) : this.IsActive.equals(that.IsActive()))
                    && ((this.JobTitle == null) ? (that.JobTitle() == null) : this.JobTitle.equals(that.JobTitle()))
                    && ((this.JobType == null) ? (that.JobType() == null) : this.JobType.equals(that.JobType()))
                    && ((this.JobTypeID == null) ? (that.JobTypeID() == null) : this.JobTypeID.equals(that.JobTypeID()))
                    && ((this.LanguageType == null) ? (that.LanguageType() == null) : this.LanguageType.equals(that.LanguageType()))
                    && ((this.StartDate == null) ? (that.StartDate() == null) : this.StartDate.equals(that.StartDate()))
                    && (this.isEng == that.isEng());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (ClassifiedJobID == null) ? 0 : this.ClassifiedJobID.hashCode();
        h *= 1000003;
        h ^= (Description == null) ? 0 : this.Description.hashCode();
        h *= 1000003;
        h ^= (EndDate == null) ? 0 : this.EndDate.hashCode();
        h *= 1000003;
        h ^= (ImageName == null) ? 0 : this.ImageName.hashCode();
        h *= 1000003;
        h ^= (IsActive == null) ? 0 : this.IsActive.hashCode();
        h *= 1000003;
        h ^= (JobTitle == null) ? 0 : this.JobTitle.hashCode();
        h *= 1000003;
        h ^= (JobType == null) ? 0 : this.JobType.hashCode();
        h *= 1000003;
        h ^= (JobTypeID == null) ? 0 : this.JobTypeID.hashCode();
        h *= 1000003;
        h ^= (LanguageType == null) ? 0 : this.LanguageType.hashCode();
        h *= 1000003;
        h ^= (StartDate == null) ? 0 : this.StartDate.hashCode();
        h *= 1000003;
        h ^= this.isEng ? 1231 : 1237;
        return h;
    }

}
