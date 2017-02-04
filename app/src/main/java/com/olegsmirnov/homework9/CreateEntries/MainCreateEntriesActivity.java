package com.olegsmirnov.homework9.CreateEntries;

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

public class MainCreateEntriesActivity extends AppCompatActivity {

    EditText etName;
    EditText etCity;
    EditText etAge;
    Button bAddOneEntry;
    Button bAddMultipleEntry;
    HotelDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entries_main);
        mDbHelper = new HotelDbHelper(this);
        etName = (EditText) findViewById(R.id.et_create_name);
        etCity = (EditText) findViewById(R.id.et_create_city);
        etAge = (EditText) findViewById(R.id.et_create_age);
        bAddOneEntry = (Button) findViewById(R.id.button_add_entry);
        bAddMultipleEntry = (Button) findViewById(R.id.button_add_entry_and_back);
        bAddOneEntry.setOnClickListener(mListener);
        bAddMultipleEntry.setOnClickListener(mListener);
    }

    private void insertGuest(String name, String city, int age) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HotelContract.GuestEntry.COLUMN_NAME, name);
        values.put(HotelContract.GuestEntry.COLUMN_CITY, city);
        values.put(HotelContract.GuestEntry.COLUMN_AGE, age);
        db.insert(HotelContract.GuestEntry.TABLE_NAME, null, values);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = etName.getText().toString();
            String city = etCity.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            etName.setText("");
            etCity.setText("");
            etAge.setText("");
            switch (view.getId()) {
                case (R.id.button_add_entry):
                    insertGuest(name, city, age);
                    Toast.makeText(getApplicationContext(), "Гость " + name + " успешно добавлен(а)", Toast.LENGTH_SHORT).show();
                    break;
                case (R.id.button_add_entry_and_back):
                    insertGuest(name, city, age);
                    Toast.makeText(getApplicationContext(), "Гость " + name + " успешно добавлен(а)", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
            }
        }
    };
}
