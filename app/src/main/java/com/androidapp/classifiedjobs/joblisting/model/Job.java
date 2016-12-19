package com.androidapp.classifiedjobs.joblisting.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapp.classifiedjobs.JobModel;
import com.androidapp.classifiedjobs.dbhelper.DatabaseManager;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.login.model.CategoryList;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan on 12-12-2016.
 */

@AutoValue
public abstract class Job implements JobModel {

    public static final Factory<Job> JOB_FACTORY = new Factory<>(AutoValue_Job::new);

    public static final RowMapper<Job> ROW_MAPPER = JOB_FACTORY.select_all_dataMapper();


    public static void insertJobList(JobData jobData) {
        if (!checkDuplication(jobData)) {
            SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
            sqLiteDatabase.insert(Job.TABLE_NAME, null,
                    Job.JOB_FACTORY.marshal(jobData).asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        } else {
            updateJobList(jobData);
        }
    }

    public static boolean checkDuplication(JobData jobData) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Job.SELECT_DATA_BY_ID, new String[]{String.valueOf(jobData.JobID())});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                return true;
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return false;
    }

    public static List<Job> getAllJobList(Context context) {
        List<Job> jobLists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = null;
        if (Prefs.with(context).getBoolean(Constants.IS_LANG_ENG, true)) {
            cursor = sqLiteDatabase.rawQuery(Job.SELECT_DATA_BY_LANG, new String[]{"E"});
        } else {
            cursor = sqLiteDatabase.rawQuery(Job.SELECT_DATA_BY_LANG, new String[]{"A"});
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                jobLists.add(Job.ROW_MAPPER.map(cursor));
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return jobLists;
    }

    public static void updateJobList(JobData jobData) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(Job.TABLE_NAME,
                Job.JOB_FACTORY.marshal(jobData).asContentValues(), Job.JOBID + " = ?", new String[]{String.valueOf(jobData.JobID())});
        DatabaseManager.getInstance().closeDatabase();
    }

    public static void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.delete(Job.TABLE_NAME,
                null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
