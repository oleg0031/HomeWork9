package com.olegsmirnov.homework9;

import android.provider.BaseColumns;

public final class HotelContract {

    private HotelContract() {}

    public static abstract class GuestEntry implements BaseColumns {
        public static final String TABLE_NAME = "guests";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_AGE = "age";
    }
}