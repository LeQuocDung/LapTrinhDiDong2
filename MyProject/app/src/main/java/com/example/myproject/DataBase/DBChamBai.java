package com.example.myproject.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myproject.ThuVien.SGiaoVien;

import java.util.ArrayList;

public class DBChamBai {
    DBHelper dbHelper;

    public DBChamBai(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(SGiaoVien sGiaoVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("magv", sGiaoVien.getsMaGV());
        values.put("hotengv", sGiaoVien.getsHoTenGV());
        values.put("sdtgv", sGiaoVien.getsSDTGV());

        db.insert("GiaoVien", null, values);

    }

    public void Sua(SGiaoVien sGiaoVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("magv", sGiaoVien.getsMaGV());
        values.put("hotengv", sGiaoVien.getsHoTenGV());
        values.put("sdtgv", sGiaoVien.getsSDTGV());

        db.update("GiaoVien", values, "magv = '" + sGiaoVien.getsMaGV() + "'", null);

    }

    public void Xoa(SGiaoVien sGiaoVien) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from GiaoVien where magv = '" + sGiaoVien.getsMaGV() + "'";
        db.execSQL(sql);
        //db.delete("GiaoVien", "magv = '" + sGiaoVien.getsMaGV() + "'", null );
        //db.close();

    }

    public ArrayList<SGiaoVien> LayDL() {
        ArrayList<SGiaoVien> data = new ArrayList<>();
        String sql = " select * from GiaoVien";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                SGiaoVien sGiaoVien = new SGiaoVien();
                sGiaoVien.setsMaGV(cursor.getString(0));
                sGiaoVien.setsHoTenGV(cursor.getString(1));

                sGiaoVien.setsSDTGV(cursor.getString(2));
                data.add(sGiaoVien);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }

        return data;
    }

    public ArrayList<SGiaoVien> LayDL(String magv) {
        ArrayList<SGiaoVien> data = new ArrayList<>();
        String sql = " select * from GiaoVien where magv ='" + magv + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                SGiaoVien sGiaoVien = new SGiaoVien();
                sGiaoVien.setsMaGV(cursor.getString(0));
                sGiaoVien.setsHoTenGV(cursor.getString(1));

                sGiaoVien.setsSDTGV(cursor.getString(2));
                data.add(sGiaoVien);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }

        return data;
    }


}
