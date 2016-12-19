package com.androidapp.classifiedjobs.joblisting.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapp.classifiedjobs.ClassifiedJobModel;
import com.androidapp.classifiedjobs.dbhelper.DatabaseManager;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan on 15-12-2016.
 */

@AutoValue
public abstract class ClassifiedJob implements ClassifiedJobModel {
    public static final Factory<ClassifiedJob> CLASSIFIED_JOB_FACTORY = new Factory<>(AutoValue_ClassifiedJob::new);

    public static final RowMapper<ClassifiedJob> ROW_MAPPER = CLASSIFIED_JOB_FACTORY.select_all_dataMapper();


    public static void insertClassifiedJobList(ClassifiedJob classifiedJob) {
        if (!checkDuplication(classifiedJob)) {
            SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
            sqLiteDatabase.insert(ClassifiedJob.TABLE_NAME, null,
                    ClassifiedJob.CLASSIFIED_JOB_FACTORY.marshal(classifiedJob).asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        } else {
            updateClassifiedJobList(classifiedJob);
        }
    }

    public static boolean checkDuplication(ClassifiedJob classifiedJob) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(ClassifiedJob.SELECT_DATA_BY_ID, new String[]{String.valueOf(classifiedJob.ClassifiedJobID())});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                return true;
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return false;
    }

    public static List<ClassifiedJob> getAllClassifiedJobList(Context context) {
        List<ClassifiedJob> classifiedJobList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = null;
        if (Prefs.with(context).getBoolean(Constants.IS_LANG_ENG, true)) {
            cursor = sqLiteDatabase.rawQuery(ClassifiedJob.SELECT_DATA_BY_LANG, new String[]{"E"});
        } else {
            cursor = sqLiteDatabase.rawQuery(ClassifiedJob.SELECT_DATA_BY_LANG, new String[]{"A"});
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                classifiedJobList.add(ClassifiedJob.ROW_MAPPER.map(cursor));
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return classifiedJobList;
    }

    public static void updateClassifiedJobList(ClassifiedJob classifiedJob) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(ClassifiedJob.TABLE_NAME,
                ClassifiedJob.CLASSIFIED_JOB_FACTORY.marshal(classifiedJob).asContentValues(), ClassifiedJob.CLASSIFIEDJOBID + " = ?", new String[]{String.valueOf(classifiedJob.ClassifiedJobID())});
        DatabaseManager.getInstance().closeDatabase();
    }


    public static void deleteAllData() {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.delete(ClassifiedJob.TABLE_NAME,
                null, null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
