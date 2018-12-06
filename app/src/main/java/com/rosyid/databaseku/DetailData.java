package com.rosyid.databaseku;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    protected Cursor cursor;
    TextView no, nama, tglLahir, jk, alamat;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        no = (TextView) findViewById(R.id.textView000);
        nama= (TextView) findViewById(R.id.textView001);
        tglLahir = (TextView) findViewById(R.id.textView002);
        jk = (TextView) findViewById(R.id.textView003);
        alamat = (TextView) findViewById(R.id.textView004);

        dataHelper = new DataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tglLahir.setText(cursor.getString(2).toString());
            jk.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }
    }
}
