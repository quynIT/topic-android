package com.nguyenducthang.cau2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class bookDao {
    public static ArrayList<Book>getAll(Context context){
        ArrayList<Book> mylist =new ArrayList<>();
        database helper = new database(context);
        SQLiteDatabase db =helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select*from Books",null);
        cursor.moveToFirst();//dua con tro ve
        while (!cursor.isAfterLast()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            int gia = cursor.getInt(2);
            Book book = new Book(ma, ten, gia);
            mylist.add(book);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mylist;
    }
    public  static  boolean insert(Context context,String masach,String tensach,int giasach){
        database helper = new database(context);
        SQLiteDatabase db =helper.getWritableDatabase();
        ContentValues values =new ContentValues();//cau lenh de insert data
        values.put("masach",masach);
        values.put("tensach",tensach);
        values.put("giasach",giasach);
        //tra lai du lieu
        long row =db.insert("Books",null, values);
        //neu row >0 la insert thanh cong neu >=0 thif insert khoong thanh cong
        return (row>0);
    }
}
