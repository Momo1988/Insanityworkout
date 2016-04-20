package com.example.zhangli.insanityworkout.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.db.InsanityDB;
import com.example.zhangli.insanityworkout.db.InsanityDatabaseHelper;
import com.example.zhangli.insanityworkout.db.ItemData;
import com.example.zhangli.insanityworkout.util.DatePickerFragment;
import com.example.zhangli.insanityworkout.util.TimePickerFragment;

/**
 * Created by zhangli on 16/4/9.
 */
public class DayContent extends BaseActivity {

    private InsanityDB insanityDB;
    private ItemData tempdata;
    private String daystr;
    private TextView day_content_text = (TextView)this.findViewById(R.id.day_content_text);;
    private TextView complete_date = (TextView)this.findViewById(R.id.complete_date);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daycontent);
        final Intent intent = getIntent();
        daystr = intent.getStringExtra("day");
        tempdata = insanityDB.getItemData(daystr);
        initview(tempdata);

        Button createDatabase = (Button) this.findViewById(R.id.confirm);
        assert createDatabase!=null;
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showTimePickerDialog(View view){
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View view){
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public String getcurrentday(){
        return daystr;
    }

    public void initview(ItemData itemData){
        day_content_text.setText(itemData.getContent());
        complete_date.setText(itemData.getTime());
    }

    public void updatedata(){
        if (complete_date.getText()!="0000"){
            tempdata.setTime(tempdata.getTime());
            tempdata.setIscomplete("1");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updatedata();
        insanityDB.updateDB(tempdata);
    }
}
