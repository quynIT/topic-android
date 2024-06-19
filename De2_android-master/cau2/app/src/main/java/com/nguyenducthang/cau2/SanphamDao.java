package com.nguyenducthang.cau2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SanphamDao {
    public static ArrayList<Sanpham>getAll(Context context){
        ArrayList<Sanpham> mylist = new ArrayList<>();
        database helper=new database(context);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT*FROM Sanphams",null);
        cursor.moveToFirst();
        mylist.clear();
        while (!cursor.isAfterLast()){
            String ma =cursor.getString(0);
            String ten=cursor.getString(1);
            int gia=Integer.parseInt(cursor.getString(2));
            Sanpham sanpham =new Sanpham(ma,ten,gia);
            mylist.add(sanpham);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mylist;
    }
    public static boolean delete(Context context, String ma) {
        database helper = new database(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsAffected = db.delete("Sanphams", "masp = ?", new String[]{ma});
        return (rowsAffected > 0);
    }
}
