package com.olegsmirnov.homework9.DeleteEntries;

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

public class DeleteMultipleEntriesActivity extends AppCompatActivity {

    HotelDbHelper mDbHelper;

    Button bDeleteEntries;
    EditText etDeleteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_multiple_entries);
        mDbHelper = new HotelDbHelper(this);
        etDeleteName = (EditText) findViewById(R.id.et_delete_multiple_name);
        bDeleteEntries = (Button) findViewById(R.id.button_delete_multiple_entries);
        bDeleteEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMultipleGuests();
                Toast.makeText(getApplicationContext(), "Гости с именем " + etDeleteName.getText().toString() + " были удалены", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteMultipleGuests() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(HotelContract.GuestEntry.TABLE_NAME, HotelContract.GuestEntry.COLUMN_NAME + " = ? ", new String[] {etDeleteName.getText().toString()});
    }
}
