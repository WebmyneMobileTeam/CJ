package com.androidapp.classifiedjobs.helper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;

import com.androidapp.classifiedjobs.R;

/**
 * Created by ishan on 09-12-2016.
 */

public class Functions {

    public static Typeface getTF(Activity activity) {
        Typeface font = Typeface.createFromAsset(
                activity.getAssets(),
                "fonts/Quicksand-Regular.ttf");
        return font;
    }

    public static void fireIntent(Activity from, Class to, boolean isNew) {
        Intent i = new Intent(from, to);
        from.startActivity(i);
        if(isNew){
            from.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }else{
            from.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);}
    }

}
