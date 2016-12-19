package com.androidapp.classifiedjobs.joblisting.model;

import android.support.annotation.Nullable;

/**
 * Created by ishan on 15-12-2016.
 */

public class JobData extends Job {

    private Long CategoryID;
    private String CategoryName;
    private String CompanyAddress;
    private String CompanyContactNo;
    private String CompanyName;
    private String CompanyURL;
    private String Description;
    private String EndDate;
    private String ImageName;
    private boolean IsActive;
    private Long JobID;
    private String JobTitle;
    private String JobType;
    private Long JobTypeID;
    private String LanguageType;
    private Long NoOfVacancy;
    private String PostedByContactNo;
    private String PostedByName;
    private String StartDate;
    private boolean isEng;

    public Long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Long categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyContactNo() {
        return CompanyContactNo;
    }

    public void setCompanyContactNo(String companyContactNo) {
        CompanyContactNo = companyContactNo;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyURL() {
        return CompanyURL;
    }

    public void setCompanyURL(String companyURL) {
        CompanyURL = companyURL;
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

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public Long getJobID() {
        return JobID;
    }

    public void setJobID(Long jobID) {
        JobID = jobID;
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

    public Long getNoOfVacancy() {
        return NoOfVacancy;
    }

    public void setNoOfVacancy(Long noOfVacancy) {
        NoOfVacancy = noOfVacancy;
    }

    public String getPostedByContactNo() {
        return PostedByContactNo;
    }

    public void setPostedByContactNo(String postedByContactNo) {
        PostedByContactNo = postedByContactNo;
    }

    public String getPostedByName() {
        return PostedByName;
    }

    public void setPostedByName(String postedByName) {
        PostedByName = postedByName;
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
    public Long CategoryID() {
        return CategoryID;
    }

    @Nullable
    @Override
    public String CategoryName() {
        return CategoryName;
    }

    @Nullable
    @Override
    public String CompanyAddress() {
        return CompanyAddress;
    }

    @Nullable
    @Override
    public String CompanyContactNo() {
        return CompanyContactNo;
    }

    @Nullable
    @Override
    public String CompanyName() {
        return CompanyName;
    }

    @Nullable
    @Override
    public String CompanyURL() {
        return CompanyURL;
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

    @Override
    public boolean IsActive() {
        return IsActive;
    }

    @Nullable
    @Override
    public Long JobID() {
        return JobID;
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
    public Long NoOfVacancy() {
        return NoOfVacancy;
    }

    @Nullable
    @Override
    public String PostedByContactNo() {
        return PostedByContactNo;
    }

    @Nullable
    @Override
    public String PostedByName() {
        return PostedByName;
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
        return "Job{"
                + "CategoryID=" + CategoryID + ", "
                + "CategoryName=" + CategoryName + ", "
                + "CompanyAddress=" + CompanyAddress + ", "
                + "CompanyContactNo=" + CompanyContactNo + ", "
                + "CompanyName=" + CompanyName + ", "
                + "CompanyURL=" + CompanyURL + ", "
                + "Description=" + Description + ", "
                + "EndDate=" + EndDate + ", "
                + "ImageName=" + ImageName + ", "
                + "IsActive=" + IsActive + ", "
                + "JobID=" + JobID + ", "
                + "JobTitle=" + JobTitle + ", "
                + "JobType=" + JobType + ", "
                + "JobTypeID=" + JobTypeID + ", "
                + "LanguageType=" + LanguageType + ", "
                + "NoOfVacancy=" + NoOfVacancy + ", "
                + "PostedByContactNo=" + PostedByContactNo + ", "
                + "PostedByName=" + PostedByName + ", "
                + "StartDate=" + StartDate + ", "
                + "isEng=" + isEng
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Job) {
            Job that = (Job) o;
            return ((this.CategoryID == null) ? (that.CategoryID() == null) : this.CategoryID.equals(that.CategoryID()))
                    && ((this.CategoryName == null) ? (that.CategoryName() == null) : this.CategoryName.equals(that.CategoryName()))
                    && ((this.CompanyAddress == null) ? (that.CompanyAddress() == null) : this.CompanyAddress.equals(that.CompanyAddress()))
                    && ((this.CompanyContactNo == null) ? (that.CompanyContactNo() == null) : this.CompanyContactNo.equals(that.CompanyContactNo()))
                    && ((this.CompanyName == null) ? (that.CompanyName() == null) : this.CompanyName.equals(that.CompanyName()))
                    && ((this.CompanyURL == null) ? (that.CompanyURL() == null) : this.CompanyURL.equals(that.CompanyURL()))
                    && ((this.Description == null) ? (that.Description() == null) : this.Description.equals(that.Description()))
                    && ((this.EndDate == null) ? (that.EndDate() == null) : this.EndDate.equals(that.EndDate()))
                    && ((this.ImageName == null) ? (that.ImageName() == null) : this.ImageName.equals(that.ImageName()))
                    && (this.IsActive == that.IsActive())
                    && ((this.JobID == null) ? (that.JobID() == null) : this.JobID.equals(that.JobID()))
                    && ((this.JobTitle == null) ? (that.JobTitle() == null) : this.JobTitle.equals(that.JobTitle()))
                    && ((this.JobType == null) ? (that.JobType() == null) : this.JobType.equals(that.JobType()))
                    && ((this.JobTypeID == null) ? (that.JobTypeID() == null) : this.JobTypeID.equals(that.JobTypeID()))
                    && ((this.LanguageType == null) ? (that.LanguageType() == null) : this.LanguageType.equals(that.LanguageType()))
                    && ((this.NoOfVacancy == null) ? (that.NoOfVacancy() == null) : this.NoOfVacancy.equals(that.NoOfVacancy()))
                    && ((this.PostedByContactNo == null) ? (that.PostedByContactNo() == null) : this.PostedByContactNo.equals(that.PostedByContactNo()))
                    && ((this.PostedByName == null) ? (that.PostedByName() == null) : this.PostedByName.equals(that.PostedByName()))
                    && ((this.StartDate == null) ? (that.StartDate() == null) : this.StartDate.equals(that.StartDate()))
                    && (this.isEng == that.isEng());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (CategoryID == null) ? 0 : this.CategoryID.hashCode();
        h *= 1000003;
        h ^= (CategoryName == null) ? 0 : this.CategoryName.hashCode();
        h *= 1000003;
        h ^= (CompanyAddress == null) ? 0 : this.CompanyAddress.hashCode();
        h *= 1000003;
        h ^= (CompanyContactNo == null) ? 0 : this.CompanyContactNo.hashCode();
        h *= 1000003;
        h ^= (CompanyName == null) ? 0 : this.CompanyName.hashCode();
        h *= 1000003;
        h ^= (CompanyURL == null) ? 0 : this.CompanyURL.hashCode();
        h *= 1000003;
        h ^= (Description == null) ? 0 : this.Description.hashCode();
        h *= 1000003;
        h ^= (EndDate == null) ? 0 : this.EndDate.hashCode();
        h *= 1000003;
        h ^= (ImageName == null) ? 0 : this.ImageName.hashCode();
        h *= 1000003;
        h ^= this.IsActive ? 1231 : 1237;
        h *= 1000003;
        h ^= (JobID == null) ? 0 : this.JobID.hashCode();
        h *= 1000003;
        h ^= (JobTitle == null) ? 0 : this.JobTitle.hashCode();
        h *= 1000003;
        h ^= (JobType == null) ? 0 : this.JobType.hashCode();
        h *= 1000003;
        h ^= (JobTypeID == null) ? 0 : this.JobTypeID.hashCode();
        h *= 1000003;
        h ^= (LanguageType == null) ? 0 : this.LanguageType.hashCode();
        h *= 1000003;
        h ^= (NoOfVacancy == null) ? 0 : this.NoOfVacancy.hashCode();
        h *= 1000003;
        h ^= (PostedByContactNo == null) ? 0 : this.PostedByContactNo.hashCode();
        h *= 1000003;
        h ^= (PostedByName == null) ? 0 : this.PostedByName.hashCode();
        h *= 1000003;
        h ^= (StartDate == null) ? 0 : this.StartDate.hashCode();
        h *= 1000003;
        h ^= this.isEng ? 1231 : 1237;
        return h;
    }
}
