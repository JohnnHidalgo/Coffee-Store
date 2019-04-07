package com.example.johnnhidalgo.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.johnnhidalgo.project.modelos.User;


public class DataBase extends SQLiteOpenHelper{
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context,"Login.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(idUser integer PRIMARY KEY AUTOINCREMENT, username text , password text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }


    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",user.getUserName());
        contentValues.put("password",user.getUserPass());
        long ins = db.insert("user", null, contentValues);
        if (ins ==-1)return false;
        else return true;
    }

    public  Boolean checkusername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =?", new String[]{username});
        if (cursor.getCount()>0)return false;
        else return true;
    }

    public Boolean usenamepassword(String username, String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{username,password});

        if (cursor.getCount()>0)return true;
        else return false;

    }

}
