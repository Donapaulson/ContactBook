package com.login_page.hp.contactbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "dbName", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table contacts(id integer primary key autoincrement," +
                "name varchar(20),number int(10))";
        sqLiteDatabase.execSQL(query);
//        Log.e("table","table created successfully");
    }

    public void openConnection(){
        sqLiteDatabase = getWritableDatabase();
    }

    public void closeConnection(){
        sqLiteDatabase.close();
    }

    public boolean executeQuery(String query){
        try{
//            Log.e("executeQuery",""+query);
            sqLiteDatabase.execSQL(query);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Cursor selectData(String query){
//        Log.e("select data Query",""+query);
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public void updateContact(String query)
    {
//        Log.e("update contact Query",""+query);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
