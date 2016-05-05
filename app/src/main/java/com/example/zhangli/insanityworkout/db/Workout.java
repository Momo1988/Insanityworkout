package com.example.zhangli.insanityworkout.db;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangli on 16/4/24.
 */
public class Workout {

    public static final String AUTHORITY = "WorkoutProvider";

    public static final String dbname = "insanity_workout.db";
    public static final String  tablename = "workout";
    public static final int dbversion = 2;
    public static final String TAG = "Workout";

    public static final  Uri uri = Uri.parse("content://" + AUTHORITY +"/"+tablename);

    public static  Uri group_uri (int id) {
        switch (id) {
            case 0:
                return Uri.parse("content://" + AUTHORITY + "/" + tablename + "/one");
            case 1:
                return Uri.parse("content://" + AUTHORITY + "/" + tablename + "/two");
            case 2:
                return Uri.parse("content://" + AUTHORITY + "/" + tablename + "/three");
        }
        return null;
    }



    public interface WorkoutColumns {
        /**
         * The unique ID for a row
         * <P> Type: INTEGER (long) </P>
         */
        public static final String ID = "_id";

        public static final String DAYITEM = "dayitem";

        public static final String EVENT = "exercise_event";

        public static final String ISCOMPLETED = "iscompleted";

        public static final String COMPLETE_TIME = "complete_time";


    }



    public static ItemData Cursor2Item(Cursor cursor){
        cursor.moveToFirst();
        ItemData itemData = new ItemData();
        int ID = cursor.getInt(cursor.getColumnIndex(Workout.WorkoutColumns.ID));
        String DAYITEM = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.DAYITEM));
        String EVENT = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.EVENT));
        String ISCOMPLETED = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.ISCOMPLETED));
        String COMPLETE_TIME = cursor.getString(cursor.getColumnIndex(Workout.WorkoutColumns.COMPLETE_TIME));
        itemData.setId(ID);
        itemData.setDay(DAYITEM);
        itemData.setContent(EVENT);
        itemData.setIscomplete(ISCOMPLETED);
        itemData.setTime(COMPLETE_TIME);

        Log.d(TAG,"ID is "+ID);
        Log.d(TAG,"DAYITEM is "+DAYITEM);
        Log.d(TAG,"EVENT is "+EVENT);
        Log.d(TAG,"ISCOMPLETED is "+ISCOMPLETED);
        Log.d(TAG,"COMPLETE_TIME is "+COMPLETE_TIME);



        return itemData;
    }

}
