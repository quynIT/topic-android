package com.nguyenducthang.cau2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context ,"Books", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Books(masach interger primary key,tensach text ,giasach interger)";
        db.execSQL(sql);
        sql = "insert into Books values('1','thang','5000')";
        db.execSQL(sql);
        sql = "insert into Books values('2','tho','2500')";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists Books ");
onCreate(db);
    }
}
