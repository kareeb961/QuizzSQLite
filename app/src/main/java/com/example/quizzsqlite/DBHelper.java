package com.example.quizzsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Studentdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Studentdetails(name TEXT , Id TEXT primary key , Gpa TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
     DB.execSQL("drop Table if exists Studentdetails");
    }
    public Boolean insertstudentdata(String name ,String Id ,String Gpa){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("Id",Id);
        contentValues.put("Gpa",Gpa);
        long result = DB.insert("Studentdetails",null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updatestudentdata(String name ,String Id ,String Gpa){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("Gpa",Gpa);
        Cursor cursor = DB.rawQuery("Select * from Studentdetails where name=?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = DB.update("Studentdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        } }
        public Boolean deletedata(String name ){
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Studentdetails where name=?",new String[]{name});
            if (cursor.getCount()>0) {
                long result = DB.delete("Studentdetails", "name=?", new String[]{name});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }else{
                return false;
            }


        }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Studentdetails", null);
      return cursor;


    }

}
