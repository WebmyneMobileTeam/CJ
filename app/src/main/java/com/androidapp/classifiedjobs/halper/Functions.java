package com.androidapp.classifiedjobs.halper;

import android.app.Activity;
import android.graphics.Typeface;

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

}
