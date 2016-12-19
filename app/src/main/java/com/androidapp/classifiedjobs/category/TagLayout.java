package com.androidapp.classifiedjobs.category;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.helper.ComplexPreferences;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.login.model.CategoryList;
import com.androidapp.classifiedjobs.login.model.RCategoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhruvil on 09-12-2016.
 */

public class TagLayout extends FlowLayout {
    private Context context;

    private List<RCategoryList> categories;

    public TagLayout(Context context) {
        super(context);
        this.context = context;
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void diaplyValues(List<RCategoryList> categories) {
        this.categories = categories;
        displayCategories();
    }

    private void displayCategories() {
        removeAllViews();
        invalidate();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.bottomMargin = 0;
        for (int i = 0; i < categories.size(); i++) {

            TextView txt = new TextView(getContext());
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            txt.setBackgroundResource(R.drawable.layout_bg);
            if (Prefs.with(context).getBoolean(Constants.IS_LANG_ENG, true)) {
                txt.setText(categories.get(i).getEngCategoryName());
            } else {
                txt.setText(categories.get(i).getAmhCategoryName());
            }
            txt.setPadding(30, 10, 30, 10);
            txt.setTextColor(Color.WHITE);
            txt.setOnClickListener(catClick);
            addView(txt, params);

        }
    }

    private View.OnClickListener catClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int clickedPos = indexOfChild(view);
            boolean isSelected = categories.get(clickedPos).isSelected();
            TextView txt = (TextView) view;

            if (isSelected == false) {
                txt.setBackgroundResource(R.drawable.layout_bg_selected);
            } else {
                txt.setBackgroundResource(R.drawable.layout_bg);
            }
            txt.setPadding(30, 10, 30, 10);
            categories.get(clickedPos).setSelected(!categories.get(clickedPos).isSelected());
            CategoryList.updateCategoryList(categories.get(clickedPos));
        }
    };

    public ArrayList<RCategoryList> getSelectedValues() {
        ArrayList<RCategoryList> values = new ArrayList<>();

        for (RCategoryList category : categories) {
            if (category.isSelected() == true) {
                values.add(category);
            }
        }

        return values;

    }


}
