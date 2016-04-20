package com.example.zhangli.insanityworkout;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangli on 16/4/9.
 */
public class DayContent extends BaseActivity {


   public MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daycontent);
        final Intent intent = getIntent();
        final String data = intent.getStringExtra("content");
        TextView DayContent = (TextView)findViewById(R.id.day_content_text);
        assert DayContent!=null;
        DayContent.setText(data);


        dbHelper = new MyDatabaseHelper(this, "workout.db", null,1);
        Button createDatabase = (Button) this.findViewById(R.id.confirm);
        assert createDatabase!=null;
        createDatabase.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dbHelper.insert(data,"111");
                dbHelper.update(String.valueOf(100),1,"222");
                finish();
            }
        });


        Button createDatabase1 = (Button) this.findViewById(R.id.confirm2);
        assert createDatabase1!=null;
        createDatabase1.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {



                //SQLiteDatabase db = dbHelper.getWritableDatabase();

               // Cursor cursor = db.query("workout", null, null, null, null, null, null);
                Cursor cursor = dbHelper.getBygroup(0);
               // Cursor cursor =dbHelper.getById(String.valueOf(100));
                if (cursor.moveToFirst()) {
                    do {
                        dbHelper.getday(cursor);

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        dbHelper.close();
    }


    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    public void showTimePickerDialog(View view){
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View view){
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }

}
