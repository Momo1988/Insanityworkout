package com.example.zhangli.insanityworkout.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zhangli.insanityworkout.model.ActivityCollector;

/**
 * Created by zhangli on 16/4/19.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BaseActivity", getClass().getSimpleName() + "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        Log.d("BaseActivity", getClass().getSimpleName() + "onDestroy");
    }


    public static void actionStart(Context context, String data1, String data2, Class toclass) {
        Intent intent = new Intent(context, toclass);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

}
