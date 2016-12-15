package com.androidapp.classifiedjobs.login.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapp.classifiedjobs.CategoryListModel;
import com.androidapp.classifiedjobs.dbhelper.DatabaseManager;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan on 14-12-2016.
 */

@AutoValue
public abstract class CategoryList implements CategoryListModel {
    public static final Factory<CategoryList> CATEGORY_LIST_FACTORY = new Factory<>(AutoValue_CategoryList::new);

    public static final RowMapper<CategoryList> ROW_MAPPER = CATEGORY_LIST_FACTORY.select_all_dataMapper();


    public static void insertCategoryList(RCategoryList rCategoryList) {
        if (!checkDuplication(rCategoryList)) {
            SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
            sqLiteDatabase.insert(CategoryList.TABLE_NAME, null,
                    CategoryList.CATEGORY_LIST_FACTORY.marshal(rCategoryList).asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        }else {
            updateCategoryList(rCategoryList);
        }
    }

    public static boolean checkDuplication(RCategoryList rCategoryList) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(CategoryList.SELECT_DATA_BY_ID, new String[]{String.valueOf(rCategoryList.CategoryID())});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                return true;
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return false;
    }

    public static List<CategoryList> getAllCategoryList() {
        List<CategoryList> categoryLists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(CategoryList.SELECT_ALL_DATA, new String[]{});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                categoryLists.add(CategoryList.ROW_MAPPER.map(cursor));
            }
        }
        DatabaseManager.getInstance().closeDatabase();
        return categoryLists;
    }

    public static void updateCategoryList(RCategoryList rCategoryList) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(CategoryList.TABLE_NAME,
                CategoryList.CATEGORY_LIST_FACTORY.marshal(rCategoryList).asContentValues(), CategoryList.CATEGORYID + " = ?", new String[]{String.valueOf(rCategoryList.CategoryID())});
        DatabaseManager.getInstance().closeDatabase();
    }
}
