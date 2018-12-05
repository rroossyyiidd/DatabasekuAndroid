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

public class InputData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnSave;
    String edit;
    EditText no, nama, tanggalLahir, jk, alamat;
    TextView textNo, textNama, textTanggalLahir, textJk, textAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        //edit text
        no = (EditText) findViewById(R.id.editText1);
        nama = (EditText) findViewById(R.id.editText2);
        tanggalLahir = (EditText) findViewById(R.id.editText3);
        jk = (EditText) findViewById(R.id.editText4);
        alamat = (EditText) findViewById(R.id.editText5);
        //button
        btnSave = (Button) findViewById(R.id.button1);
        //textView
        textNo = (TextView) findViewById(R.id.textView1);
        textNama = (TextView) findViewById(R.id.textView2);
        textTanggalLahir = (TextView) findViewById(R.id.textView3);
        textJk = (TextView) findViewById(R.id.textView4);
        textAlamat = (TextView) findViewById(R.id.textView5);

        //databaseHelper
        dataHelper = new DataHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();

                //menampung data di variable edit
                edit = no.getText().toString();
                edit = nama.getText().toString();
                edit = tanggalLahir.getText().toString();
                edit = jk.getText().toString();
                edit = alamat.getText().toString();

                if (edit.isEmpty()) {
                    //pesan jika field kosong
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //else
                    db.execSQL("insert into biodata('no', 'nama', 'tgl', 'jk', 'alamat') values('" +
                            no.getText().toString() + "','" + nama.getText().toString() + "','" + tanggalLahir.getText().toString() + "','" + jk.getText().toString() + "','"+alamat.getText().toString()+"')");

                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }
                DataMahasiswa.da.RefreshList();
            }
        });

    }
}
