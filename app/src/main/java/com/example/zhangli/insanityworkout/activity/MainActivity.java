package com.example.zhangli.insanityworkout.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.db.Workout;
import com.example.zhangli.insanityworkout.model.FragmentListener;
import com.example.zhangli.insanityworkout.util.SectionsPagerAdapter;


public class MainActivity
        extends BaseActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static final String TAG = "MainActivity";
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Uri uri = Workout.uri;
        switch (item.getItemId()) {
            case R.id.GridView:
/*
                ContentValues values = new ContentValues();


                values.put(Workout.WorkoutColumns.ISCOMPLETED, "0");
                values.put(Workout.WorkoutColumns.COMPLETE_TIME, "2012");
                values.put(Workout.WorkoutColumns.DAYITEM, "A Clash of Kings");
                values.put(Workout.WorkoutColumns.EVENT, "George Martin");

                Uri newUri = getContentResolver().insert(uri,values);
                 newId = newUri.getPathSegments().get(1);
                 */
                break;
            case R.id.ListView:
                /*
                Cursor cursor = getContentResolver().query(uri, null, null,null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.ISCOMPLETED));
                        String author = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.COMPLETE_TIME));
                        int pages = cursor.getInt(cursor.getColumnIndex (Workout.WorkoutColumns.ID));

                        Log.d("MainActivity", "book name is " + pages);
                        Log.d("MainActivity", "book author is " + author);
                    }
                    cursor.close();
                }

*/

                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
