package com.androidapp.classifiedjobs.helper;

import android.content.Context;

import com.androidapp.classifiedjobs.R;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by ishan on 07-12-2016.
 */
public class RetrofitErrorHelper {
    public static void showErrorMsg(Throwable throwable, Context context) {


        if (throwable instanceof IOException) {
            Functions.showAlertDialogWithOk(context, context.getResources().getString(R.string.internet_error_en));
        } else if (throwable instanceof SocketTimeoutException) {
            Functions.showAlertDialogWithOk(context, context.getResources().getString(R.string.time_out_en));
        } else {
            Functions.showAlertDialogWithOk(context, context.getResources().getString(R.string.server_error));
        }
    }
}
