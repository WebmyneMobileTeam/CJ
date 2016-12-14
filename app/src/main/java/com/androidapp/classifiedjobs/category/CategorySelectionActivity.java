package com.androidapp.classifiedjobs.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Functions;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.joblisting.activity.JobListingActivity;

import java.util.ArrayList;

public class CategorySelectionActivity extends AppCompatActivity {
    private TagLayout tagLayout;
    private String[] values = {"Information Technology", "Electrical Eng", "EC", "ME", "Civil", "FT"
            , "Cloud Technology", "Fitter", "ITI", "Teacher"};

    private Button btnNext;
    private TextView txtChooseCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        init();


    }

    private void init() {

        tagLayout = (TagLayout) findViewById(R.id.flowlayout);
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Category category = new Category();
            category.id = i;
            category.name = values[i];
            category.isSelected = false;
            categories.add(category);
        }

        tagLayout.diaplyValues(categories);

        btnNext = (Button) findViewById(R.id.btnNext);
        txtChooseCategories = (TextView) findViewById(R.id.txtChooseCategories);

        if (Prefs.with(CategorySelectionActivity.this).getBoolean(Constants.IS_LANG_ENG, true)) {
            btnNext.setText(R.string.Next_En);
            txtChooseCategories.setText(R.string.choose_categories_en);
        } else {
            btnNext.setText(R.string.Next_am);
            txtChooseCategories.setText(R.string.choose_categories_am);
        }

    }

    public void onclickNext(View view) {

        ArrayList<Category> selectedValues = tagLayout.getSelectedValues();

        for (Category selectedValue : selectedValues) {
            Log.e("Selected Value", selectedValue.name);

        }

        Functions.fireIntent(CategorySelectionActivity.this, JobListingActivity.class, true);


    }
}
