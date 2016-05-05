package com.example.zhangli.insanityworkout.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.db.ItemData;
import com.example.zhangli.insanityworkout.db.Workout;
import com.example.zhangli.insanityworkout.model.MyApplication;
import com.example.zhangli.insanityworkout.util.DatePickerFragment;
import com.example.zhangli.insanityworkout.util.TimePickerFragment;

/**
 * Created by zhangli on 16/4/9.
 */
public class DayContent extends BaseActivity {


    private static final String TAG = "DayContent";

    private TextView day_content_text;
    private TextView complete_date_text;
    private TextView textView;
    private ItemData itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daycontent);

        complete_date_text = (TextView) this.findViewById(R.id.complete_date);
        day_content_text = (TextView) this.findViewById(R.id.day_content_text);
        textView = (TextView) findViewById(R.id.complete_date);

        itemData = (ItemData) getIntent().getParcelableExtra("ItemData");
        initview(itemData);

        Button createDatabase = (Button) this.findViewById(R.id.confirm);
        assert createDatabase != null;
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder();
                sb.append(textView.getText());

                ContentValues values = new ContentValues();
                values.put(Workout.WorkoutColumns.COMPLETE_TIME,sb.toString());
                values.put(Workout.WorkoutColumns.ISCOMPLETED,"1");
                int ur = getContentResolver().update(Workout.uri,values,Workout.WorkoutColumns.DAYITEM+"=?",
                        new String[] {itemData.getDay()} );
                finish();
            }
        });


        Button createDatabase1 = (Button) this.findViewById(R.id.confirm2);
        assert createDatabase1 != null;
        createDatabase1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Workout.uri + "/" + itemData.getDay()) ;
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);

                Workout.Cursor2Item(cursor);


            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //initview(itemData);
    }

    public void showTimePickerDialog(View view) {
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void initview(ItemData itemData) {
        day_content_text.setText(itemData.getContent());
        if (!itemData.getTime().isEmpty()){complete_date_text.setText(itemData.getTime());}
        Log.d("initview", itemData.getContent().toString());
        Log.d("initview", itemData.getTime().toString());


    }

}
