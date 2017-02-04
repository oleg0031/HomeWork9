package com.olegsmirnov.homework9.UpdateEntries;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.olegsmirnov.homework9.HotelContract;
import com.olegsmirnov.homework9.HotelDbHelper;
import com.olegsmirnov.homework9.R;
import com.olegsmirnov.homework9.ReadEntries.ReadAllEntriesActivity;
import com.olegsmirnov.homework9.ReadEntries.ReadMultipleEntriesActivity;
import com.olegsmirnov.homework9.ReadEntries.ReadOneEntryActivity;

public class MainUpdateEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bUpdateOneEntry;
    Button bUpdateAllEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entries_main);
        mDbHelper = new HotelDbHelper(this);
        bUpdateOneEntry = (Button) findViewById(R.id.button_update_entry);
        bUpdateAllEntries = (Button) findViewById(R.id.button_update_all_entries);
        bUpdateOneEntry.setOnClickListener(mListener);
        bUpdateAllEntries.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case (R.id.button_update_entry):
                    intent = new Intent(getApplicationContext(), UpdateOneEntryActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.button_update_all_entries):
                    intent = new Intent(getApplicationContext(), UpdateAllEntriesActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
