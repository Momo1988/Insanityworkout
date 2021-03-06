package com.example.zhangli.insanityworkout.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.db.Workout;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("OnDateSet", "select year:" + year + ";month:" + month + ";day:" + day);
        showDate(year, month, day);
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.show(getFragmentManager(), "timePicker");

    }

    public void showDate(int year, int month, int day) {
        TextView textView = (TextView) getActivity().findViewById(R.id.complete_date);
        StringBuilder sb = new StringBuilder();
        sb.append("Complete Date :" + year + " " + month + " " + day);
        textView.setText(sb.toString());
    }
}

