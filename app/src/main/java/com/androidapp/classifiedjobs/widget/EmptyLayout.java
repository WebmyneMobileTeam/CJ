package com.androidapp.classifiedjobs.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.androidapp.classifiedjobs.R;
import com.androidapp.classifiedjobs.databinding.LayoutEmptyViewBinding;


/**
 * Created by ishan on 14-12-2016.
 */
public class EmptyLayout extends LinearLayout {

    private Context context;
    private LayoutInflater inflater;
    private LayoutEmptyViewBinding dataBind;


    public EmptyLayout(Context context) {
        super(context);
        if (!isInEditMode()) {
            this.context = context;
            init();
        }
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            this.context = context;
            init();
        }
    }

    private void init() {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBind = DataBindingUtil.inflate(inflater, R.layout.layout_empty_view, this, true);
    }

    public void setContent(String text) {
        dataBind.emptyTextView.setText(text);
    }

    public void setContent(String text, int icon) {
        dataBind.emptyTextView.setText(text);
        dataBind.emptyImageView.setImageResource(icon);
    }
}
