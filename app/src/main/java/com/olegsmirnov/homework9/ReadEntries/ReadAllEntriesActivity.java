package com.olegsmirnov.homework9.ReadEntries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class ReadAllEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_entries);
        mDbHelper = new HotelDbHelper(this);
        readAllDatabaseInfo();
    }

    private void readAllDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HotelContract.GuestEntry._ID,
                HotelContract.GuestEntry.COLUMN_NAME,
                HotelContract.GuestEntry.COLUMN_CITY,
                HotelContract.GuestEntry.COLUMN_AGE };

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // порядок сортировки

        TextView displayTextView = (TextView) findViewById(R.id.tv_read_all_entries);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " гостей.\n\n");
            displayTextView.append("ID | " +
                    HotelContract.GuestEntry.COLUMN_NAME + " | " +
                    HotelContract.GuestEntry.COLUMN_CITY + " | " +
                    HotelContract.GuestEntry.COLUMN_AGE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_NAME);
            int cityColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_CITY);
            int ageColumnIndex = cursor.getColumnIndex(HotelContract.GuestEntry.COLUMN_AGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentCity = cursor.getString(cityColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);
                displayTextView.append(("\n" + currentID + " | " +
                        currentName + " | " +
                        currentCity + " | " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }
    }
}
