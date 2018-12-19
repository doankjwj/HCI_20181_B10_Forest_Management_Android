package com.example.doannd.hci_2018_forestmanagement.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDataBase extends SQLiteOpenHelper {
    public AppDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void queryData(String sql)
    {
        Log.e("QUERY DATA", sql);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor getData(String sql)
    {
        Log.e("GET DATA", sql);
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }
}
