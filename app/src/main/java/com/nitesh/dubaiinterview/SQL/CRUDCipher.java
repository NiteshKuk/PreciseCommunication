package com.nitesh.dubaiinterview.SQL;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.nitesh.dubaiinterview.CommonUtils;

import net.sqlcipher.database.SQLiteDatabase;

import static com.nitesh.dubaiinterview.SQL.FeedReaderContract.FeedEntry.COLUMN_NAME_CONTACT;
import static com.nitesh.dubaiinterview.SQL.FeedReaderContract.FeedEntry.COLUMN_NAME_NAME;
import static com.nitesh.dubaiinterview.SQL.FeedReaderContract.FeedEntry.COLUMN_NAME_PASS;


public class CRUDCipher {
    private Context context;
    private String name,contact,pass;

    public CRUDCipher(Context context,String name,String contact,String pass){
        this.context = context;
        this.name = name;
        this.pass = pass;
        this.contact = contact;
    }

    public CRUDCipher(Context context){
        this.context = context;
    }

    public void insertSthToDb() {
        SQLiteDatabase db = FeedReaderDbHelper.getInstance(context).getWritableDatabase(CommonUtils.dbkey());
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, name);
        values.put(COLUMN_NAME_CONTACT, contact);
        values.put(COLUMN_NAME_PASS, pass);

        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

    }

    public boolean checkDB(){
        boolean isNotEmpty;
        SQLiteDatabase db2 = FeedReaderDbHelper.getInstance(context).getReadableDatabase(CommonUtils.dbkey());
        Cursor cursor2 = db2.rawQuery("SELECT * FROM '" + FeedReaderContract.FeedEntry.TABLE_NAME + "';", null);
        if(cursor2.getCount()!=0){
            isNotEmpty = true;
        }else {
            isNotEmpty = false;
        }
        cursor2.close();
        return isNotEmpty;
    }

    public String getContact(){
        String email = null;
        SQLiteDatabase db2 = FeedReaderDbHelper.getInstance(context).getReadableDatabase(CommonUtils.dbkey());
        Cursor cursor2 = db2.rawQuery("SELECT * FROM '" + FeedReaderContract.FeedEntry.TABLE_NAME + "';", null);
        if(cursor2.getCount()!=0){
            if(cursor2.moveToFirst()){
                do{
                    email = cursor2.getString(cursor2.getColumnIndex(COLUMN_NAME_CONTACT));
                }while(cursor2.moveToNext());
            }
            cursor2.close();
        }
        return email;
    }

    public String getPass(){
        String email = null;
        SQLiteDatabase db2 = FeedReaderDbHelper.getInstance(context).getReadableDatabase(CommonUtils.dbkey());
        Cursor cursor2 = db2.rawQuery("SELECT * FROM '" + FeedReaderContract.FeedEntry.TABLE_NAME + "';", null);
        if(cursor2.getCount()!=0){
            if(cursor2.moveToFirst()){
                do{
                    email = cursor2.getString(cursor2.getColumnIndex(COLUMN_NAME_PASS));
                }while(cursor2.moveToNext());
            }
            cursor2.close();
        }
        return email;
    }

    public String getUserName(){
        String email = null;
        SQLiteDatabase db2 = FeedReaderDbHelper.getInstance(context).getReadableDatabase(CommonUtils.dbkey());
        Cursor cursor2 = db2.rawQuery("SELECT * FROM '" + FeedReaderContract.FeedEntry.TABLE_NAME + "';", null);
        if(cursor2.getCount()!=0){
            if(cursor2.moveToFirst()){
                do{
                    email = cursor2.getString(cursor2.getColumnIndex(COLUMN_NAME_NAME));
                }while(cursor2.moveToNext());
            }
            cursor2.close();
        }
        return email;
    }

    public void deleteDB(){
        SQLiteDatabase db2 = FeedReaderDbHelper.getInstance(context).getReadableDatabase(CommonUtils.dbkey());
        db2.execSQL("DELETE FROM "+FeedReaderContract.FeedEntry.TABLE_NAME);
        db2.close();
    }
}
