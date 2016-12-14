package com.androidapp.classifiedjobs.category;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.classifiedjobs.R;

import java.util.ArrayList;

/**
 * Created by dhruvil on 09-12-2016.
 */

public class TagLayout extends FlowLayout {

    private ArrayList<Category> categories;

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void diaplyValues(ArrayList<Category> categories) {
        this.categories = categories;
        displayCategories();
    }

    private void displayCategories() {
        removeAllViews();
        invalidate();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 4;
        params.bottomMargin = 4;
        for (int i = 0; i < categories.size(); i++) {

            TextView txt = new TextView(getContext());
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            txt.setBackgroundResource(R.drawable.layout_bg);
            txt.setText(categories.get(i).name);
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
            boolean isSelected = categories.get(clickedPos).isSelected;
            TextView txt = (TextView)view;

            if(isSelected == false){
                txt.setBackgroundResource(R.drawable.layout_bg_selected);
            }else{
                txt.setBackgroundResource(R.drawable.layout_bg);
            }
            txt.setPadding(30, 10, 30, 10);
            categories.get(clickedPos).isSelected = !categories.get(clickedPos).isSelected;
        }
    };

    public ArrayList<Category> getSelectedValues(){
        ArrayList<Category> values = new ArrayList<>();

        for (Category category : categories) {
            if(category.isSelected == true){
                values.add(category);
            }

        }
        return values;

    }



}
