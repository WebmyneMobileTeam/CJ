package com.androidapp.classifiedjobs.login.model;

import android.support.annotation.Nullable;

/**
 * Created by ishan on 14-12-2016.
 */

public class RCategoryList extends CategoryList {
    private Long CategoryID;
    private String EngCategoryName;
    private String AmhCategoryName;
    private boolean isSelected;

    @Nullable
    @Override
    public Long CategoryID() {
        return CategoryID;
    }

    public Long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Long categoryID) {
        CategoryID = categoryID;
    }

    public String getEngCategoryName() {
        return EngCategoryName;
    }

    public void setEngCategoryName(String engCategoryName) {
        EngCategoryName = engCategoryName;
    }

    public String getAmhCategoryName() {
        return AmhCategoryName;
    }

    public void setAmhCategoryName(String amhCategoryName) {
        AmhCategoryName = amhCategoryName;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Nullable

    @Override
    public String EngCategoryName() {
        return EngCategoryName;
    }

    @Nullable
    @Override
    public String AmhCategoryName() {
        return AmhCategoryName;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String toString() {
        return "CategoryList{"
                + "CategoryID=" + CategoryID + ", "
                + "EngCategoryName=" + EngCategoryName + ", "
                + "AmhCategoryName=" + AmhCategoryName + ", "
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
                    && ((this.EngCategoryName == null) ? (that.EngCategoryName() == null) : this.EngCategoryName.equals(that.EngCategoryName()))
                    && ((this.AmhCategoryName == null) ? (that.AmhCategoryName() == null) : this.AmhCategoryName.equals(that.AmhCategoryName()))
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
        h ^= (EngCategoryName == null) ? 0 : this.EngCategoryName.hashCode();
        h *= 1000003;
        h ^= (AmhCategoryName == null) ? 0 : this.AmhCategoryName.hashCode();
        h *= 1000003;
        h ^= this.isSelected ? 1231 : 1237;
        return h;
    }

}
