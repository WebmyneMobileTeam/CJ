package com.androidapp.classifiedjobs.category.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.RowItemTagBinding;
import com.androidapp.classifiedjobs.helper.Constants;
import com.androidapp.classifiedjobs.helper.Prefs;
import com.androidapp.classifiedjobs.login.model.RCategoryList;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by ishan on 15-12-2016.
 */

public class TagAdapter extends BaseAdapter {

    private final List<RCategoryList> rCategoryLists;
    private Context context;
    private RowItemTagBinding dataBind;

    public TagAdapter(Context context, List<RCategoryList> rCategoryLists) {
        this.context = context;
        this.rCategoryLists = rCategoryLists;
    }

    @Override
    public int getCount() {
        return rCategoryLists.size();
    }

    @Override
    public RCategoryList getItem(int position) {
        return rCategoryLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dataBind = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_item_tag, parent, false);

        if (position % 2 == 0) {
            dataBind.txtWord.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            dataBind.txtWord.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }

        try {
            if (Prefs.with(context).getBoolean(Constants.IS_LANG_ENG, true)) {
                dataBind.txtWord.setText(rCategoryLists.get(position).getEngCategoryName());
            } else {
                dataBind.txtWord.setText(rCategoryLists.get(position).getAmhCategoryName());
            }
            dataBind.txtWord.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (rCategoryLists.get(position).isSelected()) {
                        dataBind.txtWord.setBackgroundColor(ContextCompat.getColor(context, R.color.darkGrey));
                    } else {
                        if (position % 2 == 0) {
                            dataBind.txtWord.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        } else {
                            dataBind.txtWord.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                        }
                    }
                    rCategoryLists.get(position).setSelected(!rCategoryLists.get(position).isSelected());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataBind.getRoot();
    }
}
