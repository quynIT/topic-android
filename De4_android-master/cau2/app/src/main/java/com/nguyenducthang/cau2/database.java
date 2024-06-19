package com.nguyenducthang.cau2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context, "Sanpham", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="create table Sanphams(masp text primary key,tensp text ,gia int)";
        db.execSQL(sql);
        sql ="insert into Sanphams values('1','nguyen duc thang','20000')";
        db.execSQL(sql);
        sql ="insert into Sanphams values('2','nguyen thang','2000')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Sanphams ");
        onCreate(db);
    }
}
