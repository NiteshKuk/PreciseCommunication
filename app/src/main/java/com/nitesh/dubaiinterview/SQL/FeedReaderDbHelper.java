package com.nitesh.dubaiinterview.SQL;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    private static FeedReaderDbHelper instance;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PRECISE.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + "," +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_ID + TEXT_TYPE + "," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_CONTACT + TEXT_TYPE + "," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_PASS + TEXT_TYPE /*+ ","*/ +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE /*+ ","*/ +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_ISLogout + TEXT_TYPE + "," +
//                    FeedReaderContract.FeedEntry.COLUMN_NAME_ISPASS + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static public synchronized FeedReaderDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new FeedReaderDbHelper(context);
        }
        return instance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
