package com.olegsmirnov.homework9.UpdateEntries;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;

public class UpdateAllEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    EditText etUpdateAllEntries;
    Button bUpdateAllEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_all_entries);
        mDbHelper = new HotelDbHelper(this);
        etUpdateAllEntries = (EditText) findViewById(R.id.et_update_all_age);
        bUpdateAllEntries = (Button) findViewById(R.id.button_update_all_entries);
        bUpdateAllEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAllEntries();
                Toast.makeText(getApplicationContext(), "Возраст всех посетителей изменён", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAllEntries() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_AGE, etUpdateAllEntries.getText().toString());
        db.update(HotelContract.GuestEntry.TABLE_NAME, values, null, null);
    }
}
