package com.example.zhangli.insanityworkout.util;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
    private ListAdapter adapter;
    int[] ind1 = {0,28,35};
    int[] ind2 = {28,35,62};
    List<ItemData> ll;
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
    Log.d("debug","oncreate_fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.listv);
        int id = getArguments().getInt(ARG_SECTION_NUMBER);

        ll =  ((MainActivity) getActivity()).getItemDatalist();

        adapter = new ListAdapter(getActivity(),ll.subList(ind1[id],ind2[id]));  ////

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DayContent.class);
                //String str = ((TextView) view.findViewById(R.id.list_item)).getText().toString();
                String daystr = ((TextView) view.findViewById(R.id.day)).getText().toString();
                intent.putExtra("day", daystr);
                startActivity(intent);
                Log.d("debug", "goto daycontent");
            }
        });
        Log.d("debug", "oncreateview");
        return rootView;
    }
}

