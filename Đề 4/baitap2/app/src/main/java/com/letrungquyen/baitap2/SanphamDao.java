package com.letrungquyen.baitap2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ipsec.ike.SaProposal;
import android.widget.ArrayAdapter;

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
            Sanpham sanpham =new Sanpham(ma ,ten,gia);
            mylist.add(sanpham);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mylist;
    }
    public static boolean inserts (Context context,String ma ,String ten,int gia){
        database helper =new database(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put("masp",ma);
        values.put("tensp",ten);
        values.put("gia",gia);
        long row =db.insert("Sanphams",null, values);
        return row>0;
    }
}
