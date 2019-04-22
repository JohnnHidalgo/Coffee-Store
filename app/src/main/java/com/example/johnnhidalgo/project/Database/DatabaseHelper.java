package com.example.johnnhidalgo.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.johnnhidalgo.project.modelos.Cliente;
import com.example.johnnhidalgo.project.modelos.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Project.db";

    // User table name
    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // create table sql query
    private String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USER +
                    "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_NAME + " TEXT,"
                    + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    // Cliente table name
    private static final String TABLE_CLIENTE = "cliente";

    private static final String COLUMN_CLIENTE_ID = "cliente_id";
    private static final String COLUMN_CLIENTE_NAME = "cliente_name";
    private static final String COLUMN_CLIENTE_PASSWORD = "cliente_password";


    // create table sql query
    private String CREATE_CLIENTE_TABLE =
            "CREATE TABLE " + TABLE_CLIENTE +
                    "(" + COLUMN_CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CLIENTE_NAME + " TEXT,"
                    + COLUMN_CLIENTE_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_CLIENTE_TABLE = "DROP TABLE IF EXISTS " + TABLE_CLIENTE;



    private static final String TABLE_CAFETERIA = "cafeteria";


    private static final String COLUMN_CAFETERIA_ID = "cafeteria_id";
    private static final String COLUMN_CAFETERIA_NAME = "cafeteria_name";
    private static final String COLUMN_CAFETERIA_PRICE = "cafeteria_price";
    private static final String COLUMN_CAFETERIA_IMAGEN = "cafeteria_imagen";


    // create table sql query
    private String CREATE_CAFETERIA_TABLE =
            "CREATE TABLE " + TABLE_CAFETERIA +
                    "(" + COLUMN_CAFETERIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CAFETERIA_NAME + " VARCHAR,"
                    + COLUMN_CAFETERIA_PRICE + " VARCHAR,"
                    + COLUMN_CAFETERIA_IMAGEN + " BLOB" + ")";

    // drop table sql query
    private String DROP_CAFETERIA_TABLE = "DROP TABLE IF EXISTS " + TABLE_CAFETERIA;





    private static final String TABLE_MASITAS = "masitas";


    private static final String COLUMN_MASITAS_ID = "masitas_id";
    private static final String COLUMN_MASITAS_NAME = "masitas_name";
    private static final String COLUMN_MASITAS_PRICE = "masitas_price";
    private static final String COLUMN_MASITAS_IMAGEN = "masitas_imagen";


    // create table sql query
    private String CREATE_MASITAS_TABLE =
            "CREATE TABLE " + TABLE_MASITAS +
                    "(" + COLUMN_MASITAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MASITAS_NAME + " VARCHAR,"
                    + COLUMN_MASITAS_PRICE + " VARCHAR,"
                    + COLUMN_MASITAS_IMAGEN + " BLOB" + ")";

    // drop table sql query
    private String DROP_MASITAS_TABLE = "DROP TABLE IF EXISTS " + TABLE_MASITAS;
    private SQLiteDatabase database;

    private static final String TABLE_PEDIDO = "pedido";

    private static final String COLUMN_PEDIDO_ID = "pedido_id";
    private static final String COLUMN_PEDIDO_ID_CAFETERIA = "pedido_idcafeteria";
    private static final String COLUMN_PEDIDO_CANTIDAD = "pedido_cantidad";


    // create table sql query
    private String CREATE_PEDIDO_TABLE =
            "CREATE TABLE " + TABLE_PEDIDO +
                    "(" + COLUMN_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PEDIDO_ID_CAFETERIA + " INTEGER,"
                    + COLUMN_PEDIDO_CANTIDAD + " INTEGER" + ")";
//                    + " FOREIGN KEY (COLUMN_PEDIDO_ID_CAFETERIA) REFERENCES " + ")";

    // drop table sql query
    private String DROP_PEDIDO_TABLE = "DROP TABLE IF EXISTS " + TABLE_PEDIDO;




    private static final String TABLE_VENTA = "venta";

    private static final String COLUMN_VENTA_ID = "venta_id";
    private static final String COLUMN_VENTA_ID_CAFETERIA = "venta_idcafeteria";
    private static final String COLUMN_VENTA_CANTIDAD = "venta_cantidad";


    // create table sql query
    private String CREATE_VENTA_TABLE =
            "CREATE TABLE " + TABLE_VENTA +
                    "(" + COLUMN_VENTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_VENTA_ID_CAFETERIA + " INTEGER,"
                    + COLUMN_VENTA_CANTIDAD + " INTEGER" + ")";
//                    + " FOREIGN KEY (COLUMN_PEDIDO_ID_CAFETERIA) REFERENCES " + ")";

    // drop table sql query
    private String DROP_VENTA_TABLE = "DROP TABLE IF EXISTS " + TABLE_PEDIDO;







    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLIENTE_TABLE);
        db.execSQL(CREATE_CAFETERIA_TABLE);
        db.execSQL(CREATE_MASITAS_TABLE);
        db.execSQL(CREATE_PEDIDO_TABLE);
        db.execSQL(CREATE_VENTA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CLIENTE_TABLE);
        db.execSQL(DROP_CAFETERIA_TABLE);
        db.execSQL(DROP_MASITAS_TABLE);
        db.execSQL(DROP_PEDIDO_TABLE);
        db.execSQL(DROP_VENTA_TABLE);
        // Create tables again
        onCreate(db);

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUserName());
        values.put(COLUMN_USER_PASSWORD, user.getUserPass());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setIdUser(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setUserPass(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    public void updateUser(User user,String nombre,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, nombre);
        values.put(COLUMN_USER_PASSWORD, pass);


        db.update(TABLE_USER, values, COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
        db.close();
    }


    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
        db.close();
    }

    public boolean checkUser(String username) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_NAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public boolean checkUser(String name, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {name, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }



    public void addCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTE_NAME, cliente.getClienteName());
        values.put(COLUMN_CLIENTE_PASSWORD, cliente.getClientePass());

        // Inserting Row
        db.insert(TABLE_CLIENTE, null, values);
        db.close();
    }



    public List<Cliente> getAllCliente() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CLIENTE_ID,
                COLUMN_CLIENTE_NAME,
                COLUMN_CLIENTE_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_CLIENTE_NAME + " ASC";
        List<Cliente> clienteList = new ArrayList<Cliente>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLIENTE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENTE_ID))));
                cliente.setClienteName(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENTE_NAME)));
                cliente.setClientePass(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENTE_PASSWORD)));
                clienteList.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return clienteList;
    }


    public void updateCliente(Cliente cliente,String nombre,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTE_NAME, nombre);
        values.put(COLUMN_CLIENTE_PASSWORD, pass);

        db.update(TABLE_CLIENTE, values, COLUMN_CLIENTE_NAME + " = ?",
                new String[]{String.valueOf(cliente.getClienteName())});
        db.close();
    }

    public void deleteCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENTE, COLUMN_CLIENTE_NAME + " = ?",
                new String[]{String.valueOf(cliente.getClienteName())});
        db.close();
    }



    public boolean checkCliente(String clientename) {
        String[] columns = {
                COLUMN_CLIENTE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_CLIENTE_NAME + " = ?";
        String[] selectionArgs = {clientename};
        Cursor cursor = db.query(TABLE_CLIENTE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public boolean checkCliente(String name, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_CLIENTE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_CLIENTE_NAME + " = ?" + " AND " + COLUMN_CLIENTE_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {name, password};

        Cursor cursor = db.query(TABLE_CLIENTE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }




    public void insertData(String name, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_CAFETERIA+" VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE "+TABLE_CAFETERIA+" SET cafeteria_name = ?, cafeteria_price = ?, cafeteria_imagen = ? WHERE cafeteria_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM "+TABLE_CAFETERIA+" WHERE cafeteria_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    public void insertDataMasita(String name, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_MASITAS+" VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void updateDataMasitas(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE "+TABLE_MASITAS+" SET masitas_name = ?, masitas_price = ?, masitas_imagen = ? WHERE masitas_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteDataMasitas(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM "+TABLE_MASITAS+" WHERE masitas_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getDataMasitas(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }



    public void insertPedido(int idCafeteria, int cantidad){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_PEDIDO+" VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(1, idCafeteria);
        statement.bindLong(2, cantidad);

        statement.executeInsert();
    }


    public  void deleteDataPedido(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM "+TABLE_PEDIDO+" WHERE pedido_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getDataPedido(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }



    public void insertVenta(int idCafeteria, int cantidad){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_VENTA+" VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(1, idCafeteria);
        statement.bindLong(2, cantidad);

        statement.executeInsert();
    }


    public  void deleteDataVenta(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM "+TABLE_VENTA+" WHERE pedido_id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getDataVenta(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

}