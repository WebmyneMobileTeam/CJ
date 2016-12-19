package com.androidapp.classifiedjobs.category;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.classifiedjobs.CJMyApplication;
import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.api.AppApi;
import com.androidapp.classifiedjobs.category.adapter.TagAdapter;
import com.androidapp.classifiedjobs.category.model.CategoryReq;
import com.androidapp.classifiedjobs.category.model.CategoryRes;
import com.androidapp.classifiedjobs.databinding.ActivityCategorySelectionBinding;
import com.androidapp.classifiedjobs.helper.ComplexPreferences;
import com.androidapp.classifiedjobs.login.model.CategoryList;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.activity.JobListingActivity;
import com.androidapp.classifiedjobs.login.model.RCategoryList;
import com.androidapp.classifiedjobs.login.model.RUserDetail;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySelectionActivity extends AppCompatActivity {
    private ActivityCategorySelectionBinding dataBind;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_category_selection);

        init();


    }

    private void init() {
        dialog = new SpotsDialog(this, R.style.Custom);

        List<CategoryList> categories = CategoryList.getAllCategoryList();
        List<RCategoryList> rCategories = new ArrayList<>();
        if (categories != null && categories.size() > 0) {
            for (int i = 0; i < categories.size(); i++) {
                RCategoryList rCategoryList = new RCategoryList();
                rCategoryList.setCategoryID(categories.get(i).CategoryID());
                rCategoryList.setEngCategoryName(categories.get(i).EngCategoryName());
                rCategoryList.setAmhCategoryName(categories.get(i).AmhCategoryName());
                rCategories.add(rCategoryList);
            }


            dataBind.flowlayout.diaplyValues(rCategories);

            if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
                dataBind.btnNext.setText(R.string.Next_En);
                dataBind.txtChooseCategories.setText(R.string.choose_categories_en);
            } else {
                dataBind.btnNext.setText(R.string.Next_am);
                dataBind.txtChooseCategories.setText(R.string.choose_categories_am);
            }
        } else {
            dataBind.emptyView.setContent(getString(R.string.no_record_en));
        }
    }

    public void onclickNext(View view) {

        ArrayList<RCategoryList> selectedValues = dataBind.flowlayout.getSelectedValues();

        // check user has select any category or not
        if (selectedValues.size() > 0) {

            //make list of selected category's id for store in server
            List<Long> categoryIdList = new ArrayList<>();
            for (int i = 0; i < selectedValues.size(); i++) {
                categoryIdList.add(selectedValues.get(i).CategoryID());
                CategoryList.updateCategoryList(selectedValues.get(i));
            }
            ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(CategorySelectionActivity.this, Constants.USER_DATA, MODE_PRIVATE);
            //get user for user's id
            RUserDetail user = complexPreferences.getObject(Constants.USER_OBJ, RUserDetail.class);

            CategoryReq categoryReq = new CategoryReq();
            categoryReq.setCategoryID(categoryIdList);
            categoryReq.setUserID(user.UserID());

            if (categoryReq != null) {
                //call this method for store selected categories to server and update db
                storeSelectedCategory(categoryReq);
            }
        } else {
            if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true))
                Functions.showSnack(findViewById(android.R.id.content), getString(R.string.select_category_en));
            else
                Functions.showSnack(findViewById(android.R.id.content), getString(R.string.select_category_am));
        }

    }

    private void storeSelectedCategory(CategoryReq categoryReq) {
        if (dialog != null) {
            dialog.show();
            if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true))
                dialog.setTitle(getString(R.string.loding_en));
            else
                dialog.setTitle(getString(R.string.loding_am));
        }
        Functions.logError(CategorySelectionActivity.this, CJMyApplication.getGson().toJson(categoryReq).toString());
        AppApi appApi = CJMyApplication.getRetrofit().create(AppApi.class);
        appApi.addCateogryCall(categoryReq).enqueue(new Callback<CategoryRes>() {
            @Override
            public void onResponse(Call<CategoryRes> call, Response<CategoryRes> response) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                Functions.logError(CategorySelectionActivity.this, CJMyApplication.getGson().toJson(response.body()));
                if (response.body().getResponseMsg().toString().toLowerCase().contains("success")) {
                    //this prefs use to know that user has successfully select category
                    //so after login we will know user has seleted nay category or not
                    Prefs.with(CategorySelectionActivity.this).save(Constants.CATEGORY_SELECTED, true);
                    Functions.fireIntent(CategorySelectionActivity.this, JobListingActivity.class, true);
                    finish();
                } else {
                    if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_en));
                    } else {
                        Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_am));
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryRes> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_en));
                } else {
                    Functions.showSnack(findViewById(android.R.id.content), getString(R.string.wrong_response_am));
                }
            }
        });
    }
}
