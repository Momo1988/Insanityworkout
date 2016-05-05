package com.example.zhangli.insanityworkout.model;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangli on 16/4/24.
 */
public class MyApplication extends Application {


    // MyApplication.getContex to get the contex all over the field
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
