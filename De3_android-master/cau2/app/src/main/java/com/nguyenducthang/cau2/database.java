package com.nguyenducthang.cau2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context, "Sinhvien", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table Sinhviens(masv text primary key,tensv text,lop text)";
        db.execSQL(sql);
        sql="insert into Sinhviens values('1','nguyen duc thang','cntt')";
        db.execSQL(sql);
        sql="insert into Sinhviens values ('2','nguyen phan hoai nam','cntt1')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists Sinhviens");
    onCreate(db);
    }
}
