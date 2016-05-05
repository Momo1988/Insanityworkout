package com.example.zhangli.insanityworkout.util;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.activity.DayContent;
import com.example.zhangli.insanityworkout.activity.MainActivity;
import com.example.zhangli.insanityworkout.db.InsanityDatabaseHelper;
import com.example.zhangli.insanityworkout.db.ItemData;
import com.example.zhangli.insanityworkout.db.Workout;
import com.example.zhangli.insanityworkout.model.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangli on 16/4/17.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "PlaceholderFragment";
    private ListAdapter adapter;
    List<ItemData> itemDatalist;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static PlaceholderFragment newcolor(int color) {
        int[] colorall = {Color.argb(127, 0, 255, 255), Color.argb(127, 255, 0, 255), Color.argb(127, 255, 255, 0)};
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt("ARG_color", colorall[color]);
        fragment.setArguments(args);
        return fragment;
    }

    public static PlaceholderFragment newFragment(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "oncreate_fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.listv);
        int id = getArguments().getInt(ARG_SECTION_NUMBER);

        itemDatalist =getItemGroup(Workout.group_uri(id));


        adapter = new ListAdapter(getActivity(),itemDatalist);
        //adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DayContent.class);
                String daystr = ((TextView) view.findViewById(R.id.day)).getText().toString();
                Uri uri = Uri.parse(Workout.uri+"/"+daystr);
                Cursor cursor = getContext().getContentResolver().query(uri,null,null,null,null);
                intent.putExtra("ItemData", Workout.Cursor2Item(cursor));
                cursor.close();
                startActivity(intent);
                Log.d(TAG, "goto daycontent");
            }
        });
        Log.d(TAG, "oncreateview"+id);
        return rootView;
    }

    public List<ItemData> getItemGroup(Uri uri){
        List<ItemData> lsd = new ArrayList<>();
        Cursor cursor = getContext().getContentResolver().query(uri,null,null,null,null);
        if (cursor!=null) {
            while (cursor.moveToNext()) {

               // ItemData itemData =  Workout.Cursor2Item(cursor);
                ItemData itemData = new ItemData();
                itemData.setId(cursor.getInt(cursor.getColumnIndex(Workout.WorkoutColumns.ID)));
                itemData.setDay(cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.DAYITEM)));
                itemData.setContent(cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.EVENT)));
                itemData.setIscomplete(cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.ISCOMPLETED)));
                itemData.setTime(cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.COMPLETE_TIME)));

                lsd.add(itemData);
            }
            cursor.close();
        }

        return lsd;
    }




}

