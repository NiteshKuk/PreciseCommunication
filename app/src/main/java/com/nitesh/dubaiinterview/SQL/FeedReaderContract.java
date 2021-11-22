package com.nitesh.dubaiinterview.SQL;

import android.provider.BaseColumns;

public class FeedReaderContract {
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "PRECISE";
        public static final String COLUMN_NAME_NAME= "name";
        public static final String COLUMN_NAME_CONTACT = "contact";
//        public static final String COLUMN_NAME_ID = "id";
//        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASS = "pass";
//        public static final String COLUMN_NAME_ISPASS = "ispass";
//        public static final String COLUMN_NAME_ISLogout = "islogout";
    }
}
