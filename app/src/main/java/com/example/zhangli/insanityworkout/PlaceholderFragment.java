package com.example.zhangli.insanityworkout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.util.Log.*;

/**
 * Created by zhangli on 16/4/17.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ArrayList<ArrayList<HashMap<String, Object>>> listItem;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ListAdapter adapter;
    private MyDatabaseHelper dbHelper;
    private Cursor cursor;
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
        int[] colorall= {Color.argb(127,0,255,255),Color.argb(127,255,0,255),Color.argb(127,255,255,0)};
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt("ARG_color", colorall[color]);
        fragment.setArguments(args);
        return fragment;
    }

    public static PlaceholderFragment newFragment(int sectionNumber){
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        cursor2arraymap();
    Log.d("debug","oncreate_fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.listv);
        int id = getArguments().getInt(ARG_SECTION_NUMBER);



        adapter = new ListAdapter(getActivity(),listItem.get(id));  ////

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DayContent.class);
                String str = ((TextView) view.findViewById(R.id.list_item)).getText().toString();
                intent.putExtra("content", str);
                intent.putExtra("day", id);
                startActivityForResult(intent,1);
                Log.d("debug", "goto daycontent");
            }
        });
        Log.d("debug", "oncreateview");
        return rootView;
    }




    public void cursor2arraymap(){


        dbHelper = new MyDatabaseHelper(getActivity(), "workout.db", null,1);
       listItem = new ArrayList<ArrayList<HashMap<String, Object>>>();

        for(int i = 0;i<3;i++) {
            cursor = dbHelper.getBygroup(i);
            ArrayList<HashMap<String, Object>> listItem00 = new ArrayList<HashMap<String, Object>>() ;
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("day", dbHelper.getday(cursor));
                    map.put("content", dbHelper.getcontent(cursor));
                    map.put("iscomplete", dbHelper.getiscomplete(cursor));
                    map.put("completetime", dbHelper.getcompletetime(cursor));
                    listItem00.add(map);
                } while (cursor.moveToNext());

                listItem.add(listItem00);
            }
            cursor.close();
        }
        dbHelper.close();
    }
}

