package com.example.zhangli.insanityworkout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.zhangli.insanityworkout.activity.MainActivity;
import com.example.zhangli.insanityworkout.db.Workout.WorkoutColumns;
import com.example.zhangli.insanityworkout.model.MyApplication;

/**
 * Created by zhangli on 16/4/17.
 */
public class InsanityDatabaseHelper extends SQLiteOpenHelper {




    public static final String CREATE_Workout = "create table "
            + Workout.tablename             + "("
            + WorkoutColumns.ID             + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WorkoutColumns.EVENT          + " TEXT NOT NULL DEFAULT '', "
            + WorkoutColumns.DAYITEM        + " TEXT NOT NULL DEFAULT '',"
            + WorkoutColumns.ISCOMPLETED    + " TEXT NOT NULL DEFAULT '',"
            + WorkoutColumns.COMPLETE_TIME  + " TEXT NOT NULL DEFAULT ''"
            + ")";

    private Context mContext;

    public InsanityDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数
        super(context, name, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建列表
        db.execSQL(CREATE_Workout);
        initdb(db);
        Toast.makeText(mContext, "Create succeeded" + Workout.dbname, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*switch (oldVersion){
            case 1:
                db.execSQL(CREATE_Workout);
            case 2:
                db.execSQL(CREATE_Workout);
            default:
        } */
        db.execSQL("drop table workout");
        onCreate(db);
    }

    public Cursor getAll(String where, String orderBy) {//返回表中的数据,where是调用时候传进来的搜索内容,orderby是设置中传进来的列表排序类型
        StringBuilder buf = new StringBuilder("SELECT _id, content, day, iscomplete FROM workout");

        if (where != null) {
            buf.append(" WHERE ");
            buf.append(where);
        }

        if (orderBy != null) {
            buf.append(" ORDER BY ");
            buf.append(orderBy);
        }

        return (getReadableDatabase().rawQuery(buf.toString(), null));
    }

    public Cursor getById(String id) {//根据点击事件获取id,查询数据库
        String[] args = {id};

        return (getReadableDatabase()
                .rawQuery("SELECT _id, content, day, iscomplete, completetime FROM workout WHERE _ID=?",
                        args));
    }

    public Cursor getBygroup(int id) {//根据点击事件获取id,查询数据库
        switch (id + 1) {
            case 1:
                return (getReadableDatabase()
                        .rawQuery("select * from workout order by  _id asc limit 28 offset  0",
                                null));
            case 2:
                return (getReadableDatabase()
                        .rawQuery("select * from workout order by  _id asc limit 7 offset  28",
                                null));
            case 3:
                return (getReadableDatabase()
                        .rawQuery("select * from workout order by  _id asc limit 28 offset  35",
                                null));
            default:
                return (null);
        }
    }


    public void insert(SQLiteDatabase db,String event, String dayitem) {
        ContentValues cv = new ContentValues();
        cv.put(WorkoutColumns.EVENT, event);
        cv.put(WorkoutColumns.DAYITEM, dayitem);
        // cv.put("iscomplete", iscomplete);
        // cv.put("notes", notes);
        // cv.put("phone", phone);

        db.insert(Workout.tablename, null, cv);
        //getWritableDatabase().close();
    }

    //public void update(String id, String content, String day, int iscomplete,String completetime) {
    public void update(SQLiteDatabase db,String id, int iscomplete, String completetime) {
        ContentValues cv = new ContentValues();
        String[] args = {id};

        //cv.put("content", content);
        //cv.put("day", day);
        cv.put("iscomplete", iscomplete);
        cv.put("completetime", completetime);
        // cv.put("phone", phone);

        db.update("workout", cv, "ID=?",
                args);
        //getWritableDatabase().close();
    }

    public String getcontent(Cursor c) {
        Log.d("content", c.getString(1));
        return (c.getString(1));
    }

    public String getday(Cursor c) {
        Log.d("day", c.getString(2));
        return (c.getString(2));
    }

    public String getiscomplete(Cursor c) {
        Log.d("isdone", c.getString(3));
        return (c.getString(3));
    }

    public String getcompletetime(Cursor c) {
        //Log.d("completetime",c.getString(4));
        return (c.getString(4));
    }

    public boolean isexist() {//根据点击事件获取id,查询数据库

        Cursor exist = getReadableDatabase().rawQuery("select * from workout", null);

        return !exist.moveToFirst();
    }


    public void initdb(SQLiteDatabase db) {

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


                insert(db,montht[ordert[i]], "DAY " + (i + 1 + countn[id]));
            /*    ContentValues values = new ContentValues();
                values.put(Workout.WorkoutColumns.DAYITEM, "DAY " + (i + 1 + countn[id]));
                values.put(Workout.WorkoutColumns.EVENT, montht[ordert[i]]);
                mContext.getContentResolver().insert(Workout.uri,values);
                values.clear(); */
            }
        }

        Log.d("initial_database", "Done");
    }
}







