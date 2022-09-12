package com.creditscore.creditscorecheck.loan;

import android.app.Application;

public class MyApplication extends Application {


    public static String TAG = "TAG";
    public static MyApplication application;

    public static MyApplication getInstance() {
        if (application == null)
            application = new MyApplication();
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    static {
        System.loadLibrary("native-lib");
    }
}
