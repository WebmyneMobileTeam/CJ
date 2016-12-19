package com.androidapp.classifiedjobs.login.model;

import android.database.sqlite.SQLiteDatabase;

import com.androidapp.classifiedjobs.UserDataModel;
import com.androidapp.classifiedjobs.dbhelper.DatabaseManager;
import com.google.auto.value.AutoValue;
import com.google.common.math.LongMath;

/**
 * Created by ishan on 14-12-2016.
 */

@AutoValue
public abstract class UserData implements UserDataModel {
    public static final Factory<UserData> USER_DATA_FACTORY = new Factory<>(AutoValue_UserData::new);

    public static void insertUserData(RUserDetail rUserDetail) {
        rUserDetail.setId(Long.valueOf("1"));
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(UserData.TABLE_NAME,
                UserData.USER_DATA_FACTORY.marshal(rUserDetail).asContentValues(), UserData.ID + "= ?", new String[]{String.valueOf(1)});
        DatabaseManager.getInstance().closeDatabase();
    }

}
