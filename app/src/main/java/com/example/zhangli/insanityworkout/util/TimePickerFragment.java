package com.example.zhangli.insanityworkout.util;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.activity.DayContent;
import com.example.zhangli.insanityworkout.activity.MainActivity;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //处理设置的时间，这里我们作为示例，在日志中输出我们选择的时间
        TextView textView = (TextView) getActivity().findViewById(R.id.complete_date);
        textView.setText(textView.getText().toString() + hourOfDay + ":" + minute);
        Log.d("onTimeSet", "hourOfDay: " + hourOfDay + "Minute: " + minute);
    }
}
