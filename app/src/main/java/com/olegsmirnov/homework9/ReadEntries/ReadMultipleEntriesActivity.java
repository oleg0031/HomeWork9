package com.olegsmirnov.homework9.ReadEntries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class ReadMultipleEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    EditText etReadName;
    EditText etReadCity;
    EditText etReadAge;
    Button bShowPersonsInfo;
    TextView tvPersonsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_multiple_entries);
        mDbHelper = new HotelDbHelper(this);
        etReadName = (EditText) findViewById(R.id.et_read_name);
        etReadCity = (EditText) findViewById(R.id.et_read_city);
        etReadAge = (EditText) findViewById(R.id.et_read_age);
        tvPersonsInfo = (TextView) findViewById(R.id.tv_read_multiple_persons_info);
        bShowPersonsInfo = (Button) findViewById(R.id.button_read_multiple_persons_info);
        bShowPersonsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPersonsInfo.setText("");
                readMultiplePersonsInfo();
            }
        });
    }

    private void readMultiplePersonsInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection =
                        HotelContract.GuestEntry.COLUMN_NAME + " = ? OR " +
                        HotelContract.GuestEntry.COLUMN_CITY + " = ? OR " +
                        HotelContract.GuestEntry.COLUMN_AGE + " = ?";
        String[] selectionArgs = {etReadName.getText().toString(), etReadCity.getText().toString(), etReadAge.getText().toString()};

        String[] projection = {"*"};

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // порядок сортировки

        try {
            tvPersonsInfo.append("ID | " +
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
                tvPersonsInfo.append(("\n" + currentID + " | " +
                        currentName + " | " +
                        currentCity + " | " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }
    }
}
