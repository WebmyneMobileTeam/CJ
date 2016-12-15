package com.androidapp.classifiedjobs.login.model;

import android.support.annotation.Nullable;

/**
 * Created by ishan on 14-12-2016.
 */

public class RCategoryList extends CategoryList {
    private Long CategoryID;
    private String CategoryName;
    private boolean isSelected;


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

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String toString() {
        return "CategoryList{"
                + "CategoryID=" + CategoryID + ", "
                + "CategoryName=" + CategoryName + ", "
                + "isSelected=" + isSelected
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof CategoryList) {
            CategoryList that = (CategoryList) o;
            return ((this.CategoryID == null) ? (that.CategoryID() == null) : this.CategoryID.equals(that.CategoryID()))
                    && ((this.CategoryName == null) ? (that.CategoryName() == null) : this.CategoryName.equals(that.CategoryName()))
                    && (this.isSelected == that.isSelected());
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
        h ^= this.isSelected ? 1231 : 1237;
        return h;
    }

}
