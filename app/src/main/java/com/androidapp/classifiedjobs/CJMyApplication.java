package com.androidapp.classifiedjobs;

import android.app.Application;

import com.androidapp.classifiedjobs.dbhelper.CJOpenHelper;
import com.androidapp.classifiedjobs.dbhelper.DatabaseManager;
import com.androidapp.classifiedjobs.helper.Constants;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ishan on 14-12-2016.
 */

public class CJMyApplication extends Application{
    private static CJMyApplication cjMyApplication;
    private static Retrofit retrofit;
    private static Gson gson;


    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        initStetho();
        initGson();
        initRetrofit();
    }

    private void initDataBase() {
        DatabaseManager.initialize(CJOpenHelper.getInstance((this)));
        CJOpenHelper.getInstance((this)).createDataBase(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    private void initGson() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public static Gson getGson() {
        return gson;
    }

    private void initRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        CJMyApplication.retrofit = retrofit;
    }
}
