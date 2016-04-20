package com.example.zhangli.insanityworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by zhangli on 16/4/17.
 */public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_Workout = "create table IF NOT EXISTS workout ("
            + "_id integer primary key autoincrement, "
            + "content text, "
            + "day text, "
            + "iscomplete bit DEFAULT 0,"
            + "completetime text default null"
            + ")"; //就是bit类型的 .-1代表true 0 代表false

    private Context mContext;
    private static final String DATABASE_NAME="workout.db";//数据库名称
    private static final int SCHEMA_VERSION=2;//版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数
        super(context, name, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建的是一个午餐订餐的列表,id,菜名,地址等等
        db.execSQL(CREATE_Workout);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
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
        StringBuilder buf=new StringBuilder("SELECT _id, content, day, iscomplete FROM workout");

        if (where!=null) {
            buf.append(" WHERE ");
            buf.append(where);
        }

        if (orderBy!=null) {
            buf.append(" ORDER BY ");
            buf.append(orderBy);
        }

        return(getReadableDatabase().rawQuery(buf.toString(), null));
    }

    public Cursor getById(String id) {//根据点击事件获取id,查询数据库
        String[] args={id};

        return(getReadableDatabase()
                .rawQuery("SELECT _id, content, day, iscomplete, completetime FROM workout WHERE _ID=?",
                        args));
    }

    public Cursor getBygroup(int id) {//根据点击事件获取id,查询数据库
       switch (id+1) {
           case 1:
               return(getReadableDatabase()
                       .rawQuery("select * from workout order by  _id asc limit 28 offset  0",
                               null));
           case 2:
               return(getReadableDatabase()
                       .rawQuery("select * from workout order by  _id asc limit 7 offset  28",
                               null));
           case 3:
               return(getReadableDatabase()
                       .rawQuery("select * from workout order by  _id asc limit 28 offset  35",
                               null));
           default:
                return(null);
       }
    }




    public void insert(String content, String day) {
        ContentValues cv=new ContentValues();
        cv.put("content", content);
        cv.put("day", day);
       // cv.put("iscomplete", iscomplete);
       // cv.put("notes", notes);
       // cv.put("phone", phone);

        this.getWritableDatabase().insert("workout", null, cv);
        //getWritableDatabase().close();
    }

    //public void update(String id, String content, String day, int iscomplete,String completetime) {
    public void update(String id, int iscomplete,String completetime) {
        ContentValues cv=new ContentValues();
        String[] args={id};

        //cv.put("content", content);
        //cv.put("day", day);
        cv.put("iscomplete", iscomplete);
        cv.put("completetime", completetime);
        // cv.put("phone", phone);

        getWritableDatabase().update("workout", cv, "_ID=?",
                args);
        //getWritableDatabase().close();
    }

    public String getcontent(Cursor c) {
        Log.d("content",c.getString(1) );
        return(c.getString(1));
    }

    public String getday(Cursor c) {
        Log.d("day",c.getString(2) );
        return(c.getString(2));
    }

    public String getiscomplete(Cursor c) {
        Log.d("isdone",c.getString(3));
        return(c.getString(3));
    }
    public String getcompletetime(Cursor c) {
        //Log.d("completetime",c.getString(4));
        return(c.getString(4));
    }

    public void initdb() {

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

       MyDatabaseHelper dbHelper;
        dbHelper = new MyDatabaseHelper(mContext, "workout.db", null,1);

        int[] countn = {0, 28, 35};
        for (int id = 0; id < 3; id++) {
            String[] montht = month[id];
            int[] ordert = order[id];
            for (int i = 0; i < ordert.length; i++) {
                dbHelper.insert(montht[ordert[i]], "DAY " + (i + 1 + countn[id]));
                dbHelper.close();
            }
        }

        Log.d("initial_database", "Done");
    }

    public boolean isexist() {//根据点击事件获取id,查询数据库

        Cursor exist = getReadableDatabase().rawQuery("select * from workout", null);

        return !exist.moveToFirst();
    }
}







