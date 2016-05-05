package com.example.zhangli.insanityworkout.db;

/**
 * Created by zhangli on 16/4/24.
 */
import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.zhangli.insanityworkout.db.Workout;
import com.example.zhangli.insanityworkout.db.Workout.WorkoutColumns;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WorkoutProvider extends ContentProvider {
    public static final String AUTHORITY = "WorkoutProvider";
    private static UriMatcher uriMatcher;
    private InsanityDatabaseHelper dbHelper;
    private static final String TAG = "WorkoutProvider";

    static {
        uriMatcher = new UriMatcher(uriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Workout.tablename, 0);
        uriMatcher.addURI(AUTHORITY, Workout.tablename+"/one", 1);
        uriMatcher.addURI(AUTHORITY, Workout.tablename+"/two", 2);
        uriMatcher.addURI(AUTHORITY, Workout.tablename+"/three", 3);
        uriMatcher.addURI(AUTHORITY, Workout.tablename+"/*", 4);
        uriMatcher.addURI(AUTHORITY, Workout.tablename+"/init", 5);
    }

    private static String SELECT_GROUP = "SELECT * "
            + " FROM " + Workout.tablename
            + " WHERE " + WorkoutColumns.ID + " >=? AND "
            + WorkoutColumns.ID + " <=? ";

    private static String ITEMquery= "SELECT * "
            + " FROM " + Workout.tablename
            + " WHERE " + WorkoutColumns.DAYITEM + " = ?";



    @Override
    public boolean onCreate() {
        dbHelper = new InsanityDatabaseHelper(getContext(), Workout.dbname, null, Workout.dbversion);
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
       // Log.d(TAG,"" + uriMatcher.match(uri));
        switch (uriMatcher.match(uri)) {
            case 0:
                cursor = db.query(Workout.tablename,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case 1:
                cursor = db.rawQuery(SELECT_GROUP,new String[] {"1","28"});
                break;
            case 2:
                cursor = db.rawQuery(SELECT_GROUP,new String[] {"29","35"});
                break;
            case 3:
                cursor = db.rawQuery(SELECT_GROUP,new String[] {"36","63"});
                break;
            case 4:
                cursor = db.rawQuery(ITEMquery,new String[] {uri.getPathSegments().get(1)});
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        int ll = uriMatcher.match(uri);
        switch (uriMatcher.match(uri)) {
            case 0:
                long newBookId = db.insert(Workout.tablename, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/" + Workout.tablename + "/" + newBookId);
                break;
            case 5:
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case 0:
                updatedRows = db.update(Workout.tablename, values, selection, selectionArgs);
                Log.d(TAG,"update " + updatedRows);
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case 0:
                deletedRows = db.delete(Workout.tablename, selection, selectionArgs);
                break;
            default:
                break;
        }
        return deletedRows;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case 0:
                return "vnd.android.cursor.dir/vnd." + Workout.AUTHORITY + "." + Workout.tablename;
            default:
                break;
        }
        return null;
    }


    boolean isempty(){

      Cursor cursor = this.query(Workout.uri,null, WorkoutColumns.DAYITEM + "=?",new String[] {"Day 1"},null);

       return this.query(Workout.uri,null,null,null,null)==null;

    }



}