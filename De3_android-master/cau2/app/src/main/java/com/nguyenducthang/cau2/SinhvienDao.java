package com.nguyenducthang.cau2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SinhvienDao {

public  static  ArrayList<Sinhvien>getAll(Context context){
    ArrayList<Sinhvien> mylist =new ArrayList<>();
    database helper =new database(context);
    SQLiteDatabase db =helper.getReadableDatabase();
    Cursor cursor = db.rawQuery("select*from Sinhviens",null);
    cursor.moveToFirst();
    mylist.clear();
    while (!cursor.isAfterLast()){
        String ma = cursor.getString(0);
        String ten =cursor.getString(1);
        String lop=cursor.getString(2);
        Sinhvien sinhvien =new Sinhvien(ma,ten,lop);
        mylist.add(sinhvien);
        cursor.moveToNext();


    }
    cursor.close();
    db.close();
    return mylist;
}
public static boolean insert (Context context,String ma ,String ten,String lop){
    database helper =new database(context);
    SQLiteDatabase db =helper.getWritableDatabase();
    ContentValues values =new ContentValues();
    values.put("masv",ma);
    values.put("tensv",ten);
    values.put("lop",lop);
    long row =db.insert("Sinhviens",null, values);
    return  (row>0);
}
//public static boolean delete (Context context,String ma ,String ten,String lop){
//    database helper =new database(context);
//    SQLiteDatabase db =helper.getWritableDatabase();
//    ContentValues values =new ContentValues();
//    values.put("masv",ma);
//    values.put("tensv",ten);
//    values.put("lop",lop);
//    long row =db.insert("Sinhviens",null, values);
//    return  (row>0);
//}

}
