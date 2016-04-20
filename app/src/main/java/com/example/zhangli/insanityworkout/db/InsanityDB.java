package com.example.zhangli.insanityworkout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangli on 16/4/20.
 */
public class InsanityDB {

    /**
     * 数据库名 */
    public static final String DB_NAME = "insanity_workout";
    /**
     * 数据库版本 */
    public static final int VERSION = 1;
    private static InsanityDB insanityDB;
    private static SQLiteDatabase db;
    /**
     * 将构造方法私有化 */
    private InsanityDB(Context context) {
        InsanityDatabaseHelper dbHelper = new InsanityDatabaseHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例。 */
    public synchronized static InsanityDB getInstance(Context context) { if (insanityDB == null) {
        insanityDB = new InsanityDB(context);
    }
        //initDB();
        return insanityDB;
    }

    public void update(String id, int iscomplete,String completetime) {
        ContentValues cv=new ContentValues();
        String[] args={id};

        //cv.put("content", content);
        //cv.put("day", day);
        cv.put("iscomplete", iscomplete);
        cv.put("completetime", completetime);
        // cv.put("phone", phone);

        db.update("workout", cv, "ID=?",
                args);
        //getWritableDatabase().close();
    }





    public static void initDB() {

        String[][] month = {{"Fit Test",
                "Plyometric Cardio Circuit",
                "Cardio Power & Resistance",
                "Cardio Recovery",
                "Pure Cardio",
                "Plyometric Cardio Circuit",
                "Rest",
                "Pure Cardio & Cardio Abs"},
                {"Core Cardio & Balance", "Rest"},
                {"Fit Test & Max Interval Circuit", "Max Interval Plyo", "Max Cardio Conditioning", "Max Recovery",
                        "Max Interval Circuit", "Rest", "Max cardio Conditioning & Cardio Abs", "Core Cardio and Balance", "Fit Test"}};
        int[][] order = {{0, 1, 2, 3, 4, 5, 6,
                2, 4, 5, 3, 2, 7, 6,
                0, 1, 7, 3, 2, 1, 6,
                7, 2, 1, 3, 7, 1, 6},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 1, 2, 3, 4, 1, 5, 2, 4, 1, 3, 6, 7, 5, 0, 1, 6, 3, 4, 7, 5, 1, 6, 4, 7, 1, 6, 8}};

        int[] countn = {0, 28, 35};
        for (int id = 0; id < 3; id++) {
            String[] montht = month[id];
            int[] ordert = order[id];
            for (int i = 0; i < ordert.length; i++) {
                ContentValues values = new ContentValues();
                values.put("day", "DAY " + (i + 1 + countn[id]));
                values.put("content", montht[ordert[i]]);
                db.insert("workout", null, values);
            }
        }

        Log.d("initial_database", "Done");
    }


    /**
     * 从数据库读取所有的Item信息。 */
    public List<ItemData> loadItemData() {
        List<ItemData> list = new ArrayList<ItemData>();
        Cursor cursor = db
                .query("workout", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ItemData itemData = new ItemData();
                itemData.setId(cursor.getInt(cursor.getColumnIndex("id")));
                itemData.setDay(cursor.getString(cursor.getColumnIndex("day")));
                itemData.setContent(cursor.getString(cursor.getColumnIndex("content")));
                list.add(itemData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ItemData getItemData(String day){


       ItemData ItemData = new ItemData();
        Cursor cursor = db
                .rawQuery("select * from workout WHERE DAY=?", new String[]{day});
        if (cursor.moveToFirst()) {
            do {
                ItemData itemData = new ItemData();
                itemData.setId(cursor.getInt(cursor.getColumnIndex("id")));
                itemData.setDay(cursor.getString(cursor.getColumnIndex("day")));
                itemData.setContent(cursor.getString(cursor.getColumnIndex("content")));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return ItemData;
    }

    public void updateDB(ItemData itemData){
        db.execSQL("update workout set iscomplete = ? time = ? where day = ?", new String[] {itemData.getIscomplete(),
                                                                                        itemData.getTime(),
                                                                                        itemData.getDay()});
    }




}
