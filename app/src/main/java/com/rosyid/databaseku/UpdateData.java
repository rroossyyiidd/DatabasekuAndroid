package com.rosyid.databaseku;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button button;
    EditText no, nama, alamat, jk, tglLahir;
    String edit;
    TextView txtNo, txtNama, txtTglLahir, txtJk, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        no = (EditText) findViewById(R.id.editText11);
        nama = (EditText) findViewById(R.id.editText12);
        tglLahir = (EditText) findViewById(R.id.editText13);
        jk = (EditText) findViewById(R.id.editText14);
        alamat = (EditText) findViewById(R.id.editText15);

        txtNo = (TextView) findViewById(R.id.textView11);
        txtNama = (TextView) findViewById(R.id.textView12);
        txtTglLahir = (TextView) findViewById(R.id.textView13);
        txtJk = (TextView) findViewById(R.id.textView14);
        txtAlamat = (TextView) findViewById(R.id.textView15);

        button = (Button) findViewById(R.id.button11);

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama ='" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tglLahir.setText(cursor.getString(2).toString());
            jk.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                edit = no.getText().toString();
                edit = nama.getText().toString();
                edit = tglLahir.getText().toString();
                edit = jk.getText().toString();
                edit = alamat.getText().toString();

                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    db.execSQL("update biodata set nama = '" + nama.getText().toString() + "'," +
                            "tgl = '" + tglLahir.getText().toString() + "'," +
                            "jk = '" + jk.getText().toString() + "'," +
                            "alamat = '" + alamat.getText().toString() + "'," +
                            "where no ='" + no.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }

                DataMahasiswa.da.RefreshList();

            }
        });

    }
}
