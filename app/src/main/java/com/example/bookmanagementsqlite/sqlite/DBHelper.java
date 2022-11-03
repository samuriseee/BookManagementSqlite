package com.example.bookmanagementsqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ComputerManagement.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE category (categoryId TEXT PRIMARY KEY, cateDescription TEXT)";
        String sql2 = "CREATE TABLE computer (Id TEXT PRIMARY KEY, computerName TEXT, computerPrice TEXT ,"+" categoryId TEXT constraint categoryId references category(categoryId))";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS category";
        String sql2 = "DROP TABLE IF EXISTS computer";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        onCreate(sqLiteDatabase);
    }
    public Boolean insertComputer(String id, String name,String price, String categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",id);
        contentValues.put("computerName",name);
        contentValues.put("computerPrice",price);
        long result = db.insert("computer",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean insertCategory(String id, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryId",id);
        contentValues.put("cateDescription",description);
        long result = db.insert("category",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateComputer(String id, String name,String price, String categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",id);
        contentValues.put("computerName",name);
        contentValues.put("computerPrice",price);
        Cursor cursor = db.rawQuery("SELECT * FROM computer WHERE Id = ?",new String[] {id});
        if(cursor.getCount()>0){
            long result = db.update("computer",contentValues,"Id=?",new String[] {id});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean DeleteComputer(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM computer WHERE Id = ?",new String[] {id});
        if(cursor.getCount()>0){
            long result = db.delete("computer","Id=?",new String[] {id});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor getAllComputer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM computer",null);
        return cursor;
    }
    public Cursor getComputerById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM computer WHERE Id = ?",new String[] {id});
        return cursor;
    }
}
