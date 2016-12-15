package com.androidapp.classifiedjobs.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.androidapp.classifiedjobs.R;

/**
 * Created by ishan on 09-12-2016.
 */

public class Functions {

    public static OkDialogDismissListener okDialogDismissListener;

    public static Typeface getTF(Activity activity) {
        Typeface font = Typeface.createFromAsset(
                activity.getAssets(),
                "fonts/Quicksand-Regular.ttf");
        return font;
    }

    public static void fireIntent(Activity from, Class to, boolean isNew) {
        Intent i = new Intent(from, to);
        from.startActivity(i);
        if (isNew) {
            from.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            from.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    public static void showSnack(View v, String msg) {
        Snackbar snack = Snackbar.make(v, msg, Snackbar.LENGTH_LONG);
        int col = Color.parseColor("#313335");
        snack.getView().setBackgroundColor(col);
        snack.setActionTextColor(Color.parseColor("#FFFFFF"));
        snack.show();
    }

    public static boolean isInternetConnected(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static void logError(Activity activity, String msg) {
        if (msg != null)
            Log.e(activity.getClass().getSimpleName(), msg);
        else
            Log.e(activity.getClass().getSimpleName(), "Null");
    }


    public static void showAlertDialogWithOk(Context mContext,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> {
                    if (okDialogDismissListener != null)
                        okDialogDismissListener.onDismiss();
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public interface OkDialogDismissListener {
        void onDismiss();
    }


    public static void setOkDialogDismissListener(OkDialogDismissListener _okDialogDismissListener) {
        okDialogDismissListener = _okDialogDismissListener;
    }
}
