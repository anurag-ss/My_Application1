package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USER_RECORD";
    private static final String Table_NAME = "USER_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +Table_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT , EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + Table_NAME);
        onCreate(db);
    }

    public boolean registerUser(String username, String email, String password ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, username);
        values.put(COL_3, email);
        values.put(COL_4, password);

        long result = db.insert(Table_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COL_2, COL_4};
        String selection = "select username from USER_DATA  where username = '" + username + "' and password = '" + password + "'";
        String [] selectionargs = {username};
        //Cursor cursor = db.query(Table_NAME, columns, null, selectionargs, null,null, null);
      //  Cursor cursor = db.rawQuery("select username from USER_DATA  where username = " + username + " and password = " + password, null);
        Cursor cursor = db.rawQuery(selection, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count>0)
            return true;
        else
            return false;

    }
}
