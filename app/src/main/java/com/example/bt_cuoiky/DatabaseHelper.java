package com.example.bt_cuoiky;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TaiKhoan.db";
    private static final String TABLE_NAME="TaiKhoan";
    private static final int VERSION=1;
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";
    private static final String COLUMN_FULLNAME="fullname";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PHONE="phone";
    private static final String TABLE_CREATE="create table TaiKhoan(username text primary key not null,"+ "password text not null,"+ "fullname text not null,"+ "email text not null,"+ "phone text not null);";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    public void QueryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database= getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query= "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public void insertTaiKhoan(TaiKhoan tk){
        db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_USERNAME,tk.getUserName());
        values.put(COLUMN_PASSWORD,tk.getPassword());
        values.put(COLUMN_FULLNAME,tk.getFullname());
        values.put(COLUMN_EMAIL,tk.getEmail());
        values.put(COLUMN_PHONE,tk.getPhone());
        db.insert(TABLE_NAME,null,values);
        db.close();
    } 
}