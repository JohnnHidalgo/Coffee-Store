package com.example.johnnhidalgo.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.johnnhidalgo.project.modelos.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase extends SQLiteOpenHelper {

    public AdminDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA user_version = 2");
        db.execSQL("PRAGMA foreign_keys = ON");

        String dbPath = db.getPath();
        android.util.Log.d(this.getClass().getSimpleName(),"******************dbpath: "+dbPath);

        String	sql = "CREATE	TABLE Admin(idAdmin  INTEGER PRIMARY KEY AUTOINCREMENT, nombreAdmin TEXT, contraseñaAdmin TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Admin");
        onCreate(db);

    }

    public void addAdmin(Admin admin) {
        ContentValues values = new ContentValues();
        //values.put("idser", servicio.getIdser());
        values.put("nomAdmin", admin.getNombreAdmin());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Admin", null, values);
    }


    public List<Admin> listaServ(){
        String sql = "select * from Admin";
        SQLiteDatabase db = this.getReadableDatabase();
        List<Admin> storeAdmin = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int idAdmin = Integer.parseInt(cursor.getString(0));
                String nombreAdmin = cursor.getString(1);
                String contraseñaAdmin = cursor.getString(2);
                storeAdmin.add(new Admin(idAdmin, nombreAdmin, contraseñaAdmin));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeAdmin;
    }


}
