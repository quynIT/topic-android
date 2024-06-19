package com.letrungquyen.baitap2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context) {
        super(context, "Sanpham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="Create table Sanphams(masp text primary key,tensp text,gia text)";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp1','VertuConstellation','10000')";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp2','Iphone 5s','10000')";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp3','Nokia Lumia 925','10000')";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp4','Samsung Galaxy S4','10000')";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp5','HTC Desire 600','10000')";
        db.execSQL(sql);
        sql="insert into Sanphams values('sp6','HKPhone Revo LEAD','10000')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="Drop table if exists Sanphams";
        onCreate(db);
    }
}
