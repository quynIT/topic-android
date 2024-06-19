package com.letrungquyen.test;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Databases extends SQLiteOpenHelper {
    public static  final String DB_NAME = "oto.sqlite";
    public static  final int DB_VERSION = 1;
    public static  final String TBL_NAME = "Oto";
    public static  final String COL_NAME = "OtoName";
    public static  final String COL_CODE = "OtoCode";
    public static  final String COL_PRICE = "OtoPrice";
    public static  final String COL_DES = "OtoDes";
    public static  final String COL_IMAGE = "OtoImage";
    public static  final String COL_CATE = "OtoCate";

    public Databases(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Databases(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public Databases(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" +COL_CODE + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +COL_NAME + " VARCHAR(100), " + COL_PRICE + " REAL, "+ COL_DES + " VARCHAR(100), "+ COL_IMAGE + " BLOB, "+ COL_CATE + " VARCHAR(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }


    //SELECT
    public Cursor queryData(String sql){
        SQLiteDatabase db =getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    //INSERT, UPDATE , DELETE
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }


    public boolean insertData(String name ,  double price , String des, byte[] photo,String type ) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + " VALUES(null,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, name);
            statement.bindString(2, String.valueOf(price));
            statement.bindString(3, des);
            statement.bindBlob(4, photo);
            statement.bindString(5, type);
            statement.executeInsert();

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public int getNumbOfRow(){
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME);
        int numRow = cursor.getCount();
        cursor.close();
        return numRow;
    }

    public boolean codeExists(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT count(*) FROM " + TBL_NAME + " WHERE " + COL_CODE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] { code });
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            db.close();
            return count > 0;
        }
        return false;
    }

    public void CreateSampleData(){
        if (getNumbOfRow() == 0){
            try {
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'MecS450',19000,'HCM', null,'Xe 4 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'MecC200',15000,'HCM', null,'Xe 4 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Xe chở hàng',20000,'Hà Nội',null,'Xe 6 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Continer',16000, 'Hải Phòng',null,'Xe 10 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Honda',17000,'Phú Yên',null,'Xe 4 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Xe chở gỗ',14000,'Phú Thọ',null,'Xe 8 bánh')");
                execSql("INSERT INTO " + TBL_NAME + " VALUES(null, 'Xe chở vật liệu',14000,'Nha Trang',null,'Xe 8 bánh')");
            }catch(Exception e){
                Log.e("error: ",e.getMessage().toString());
            }
        }
    }
}
