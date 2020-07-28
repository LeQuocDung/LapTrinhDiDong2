package com.example.myproject.DataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "QLChamBai", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="Create table GiaoVien (magv Text PRIMARY KEY, hotengv text , sdtgv text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
