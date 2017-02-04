package com.olegsmirnov.homework9.ReadEntries;

import android.content.Intent;
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

public class ReadOneEntryActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bShowPersonInfo;
    EditText etReadId;
    TextView tvPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_one_entry);
        mDbHelper = new HotelDbHelper(this);
        tvPersonInfo = (TextView) findViewById(R.id.tv_read_person_info);
        etReadId = (EditText) findViewById(R.id.et_read_id);
        bShowPersonInfo = (Button) findViewById(R.id.button_read_show_person_info);
        bShowPersonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPersonInfo.setText("");
                readOnePersonInfo();
            }
        });
    }

    private void readOnePersonInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = HotelContract.GuestEntry._ID + " = ?";
        String[] selectionArgs = {etReadId.getText().toString()};

        String[] projection = {
                HotelContract.GuestEntry._ID,
                HotelContract.GuestEntry.COLUMN_NAME,
                HotelContract.GuestEntry.COLUMN_CITY,
                HotelContract.GuestEntry.COLUMN_AGE };

        Cursor cursor = db.query(
                HotelContract.GuestEntry.TABLE_NAME, // таблица
                projection,            // столбцы
                selection,             // столбцы для условия WHERE
                selectionArgs,         // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // порядок сортировки

        try {
            tvPersonInfo.append("ID | " +
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
                tvPersonInfo.append(("\n" + currentID + " | " +
                        currentName + " | " +
                        currentCity + " | " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }
    }
}
