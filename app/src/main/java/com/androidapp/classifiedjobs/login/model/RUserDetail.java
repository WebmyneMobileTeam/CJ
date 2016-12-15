package com.androidapp.classifiedjobs.login.model;

import android.support.annotation.Nullable;

/**
 * Created by ishan on 14-12-2016.
 */

public class RUserDetail extends UserData {
    private Long id;
    private String CreatedDate;
    private Long CreatedDateInt;
    private String DeviceID;
    private String DeviceModel;
    private String DeviceToken;
    private String DeviceType;
    private String Email;
    private boolean IsAdmin;
    private boolean IsLoggedIn;
    private String LanguageType;
    private String MobileNo;
    private String Password;
    private Long UserID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public Long getCreatedDateInt() {
        return CreatedDateInt;
    }

    public void setCreatedDateInt(Long createdDateInt) {
        CreatedDateInt = createdDateInt;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        DeviceModel = deviceModel;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public boolean isLoggedIn() {
        return IsLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        IsLoggedIn = loggedIn;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    private String UserName;

    @Nullable
    @Override
    public Long id() {
        return id;
    }

    @Nullable
    @Override
    public String CreatedDate() {
        return CreatedDate;
    }

    @Nullable
    @Override
    public Long CreatedDateInt() {
        return CreatedDateInt;
    }

    @Nullable
    @Override
    public String DeviceID() {
        return DeviceID;
    }

    @Nullable
    @Override
    public String DeviceModel() {
        return DeviceModel;
    }

    @Nullable
    @Override
    public String DeviceToken() {
        return DeviceToken;
    }

    @Nullable
    @Override
    public String DeviceType() {
        return DeviceType;
    }

    @Nullable
    @Override
    public String Email() {
        return Email;
    }

    @Override
    public boolean IsAdmin() {
        return IsAdmin;
    }

    @Override
    public boolean IsLoggedIn() {
        return IsLoggedIn;
    }

    @Nullable
    @Override
    public String LanguageType() {
        return LanguageType;
    }

    @Nullable
    @Override
    public String MobileNo() {
        return MobileNo;
    }

    @Nullable
    @Override
    public String Password() {
        return Password;
    }

    @Nullable
    @Override
    public Long UserID() {
        return UserID;
    }

    @Nullable
    @Override
    public String UserName() {
        return UserName;
    }

    @Override
    public String toString() {
        return "UserData{"
                + "id=" + id + ", "
                + "CreatedDate=" + CreatedDate + ", "
                + "CreatedDateInt=" + CreatedDateInt + ", "
                + "DeviceID=" + DeviceID + ", "
                + "DeviceModel=" + DeviceModel + ", "
                + "DeviceToken=" + DeviceToken + ", "
                + "DeviceType=" + DeviceType + ", "
                + "Email=" + Email + ", "
                + "IsAdmin=" + IsAdmin + ", "
                + "IsLoggedIn=" + IsLoggedIn + ", "
                + "LanguageType=" + LanguageType + ", "
                + "MobileNo=" + MobileNo + ", "
                + "Password=" + Password + ", "
                + "UserID=" + UserID + ", "
                + "UserName=" + UserName
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof UserData) {
            UserData that = (UserData) o;
            return ((this.id == null) ? (that.id() == null) : this.id.equals(that.id()))
                    && ((this.CreatedDate == null) ? (that.CreatedDate() == null) : this.CreatedDate.equals(that.CreatedDate()))
                    && ((this.CreatedDateInt == null) ? (that.CreatedDateInt() == null) : this.CreatedDateInt.equals(that.CreatedDateInt()))
                    && ((this.DeviceID == null) ? (that.DeviceID() == null) : this.DeviceID.equals(that.DeviceID()))
                    && ((this.DeviceModel == null) ? (that.DeviceModel() == null) : this.DeviceModel.equals(that.DeviceModel()))
                    && ((this.DeviceToken == null) ? (that.DeviceToken() == null) : this.DeviceToken.equals(that.DeviceToken()))
                    && ((this.DeviceType == null) ? (that.DeviceType() == null) : this.DeviceType.equals(that.DeviceType()))
                    && ((this.Email == null) ? (that.Email() == null) : this.Email.equals(that.Email()))
                    && (this.IsAdmin == that.IsAdmin())
                    && (this.IsLoggedIn == that.IsLoggedIn())
                    && ((this.LanguageType == null) ? (that.LanguageType() == null) : this.LanguageType.equals(that.LanguageType()))
                    && ((this.MobileNo == null) ? (that.MobileNo() == null) : this.MobileNo.equals(that.MobileNo()))
                    && ((this.Password == null) ? (that.Password() == null) : this.Password.equals(that.Password()))
                    && ((this.UserID == null) ? (that.UserID() == null) : this.UserID.equals(that.UserID()))
                    && ((this.UserName == null) ? (that.UserName() == null) : this.UserName.equals(that.UserName()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (id == null) ? 0 : this.id.hashCode();
        h *= 1000003;
        h ^= (CreatedDate == null) ? 0 : this.CreatedDate.hashCode();
        h *= 1000003;
        h ^= (CreatedDateInt == null) ? 0 : this.CreatedDateInt.hashCode();
        h *= 1000003;
        h ^= (DeviceID == null) ? 0 : this.DeviceID.hashCode();
        h *= 1000003;
        h ^= (DeviceModel == null) ? 0 : this.DeviceModel.hashCode();
        h *= 1000003;
        h ^= (DeviceToken == null) ? 0 : this.DeviceToken.hashCode();
        h *= 1000003;
        h ^= (DeviceType == null) ? 0 : this.DeviceType.hashCode();
        h *= 1000003;
        h ^= (Email == null) ? 0 : this.Email.hashCode();
        h *= 1000003;
        h ^= this.IsAdmin ? 1231 : 1237;
        h *= 1000003;
        h ^= this.IsLoggedIn ? 1231 : 1237;
        h *= 1000003;
        h ^= (LanguageType == null) ? 0 : this.LanguageType.hashCode();
        h *= 1000003;
        h ^= (MobileNo == null) ? 0 : this.MobileNo.hashCode();
        h *= 1000003;
        h ^= (Password == null) ? 0 : this.Password.hashCode();
        h *= 1000003;
        h ^= (UserID == null) ? 0 : this.UserID.hashCode();
        h *= 1000003;
        h ^= (UserName == null) ? 0 : this.UserName.hashCode();
        return h;
    }



   /* String CreatedDate;
    String DeviceID;
    String DeviceModel;
    String DeviceToken;
    String DeviceType;
    String Email;
    boolean IsAdmin;
    boolean IsLoggedIn;
    String LanguageType;
    String MobileNo;
    String assword;
    int UserID;
    String UserName;

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        DeviceModel = deviceModel;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public boolean isLoggedIn() {
        return IsLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        IsLoggedIn = loggedIn;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAssword() {
        return assword;
    }

    public void setAssword(String assword) {
        this.assword = assword;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }*/
}
