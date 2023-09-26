package com.example.spaceman2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Filteri extends AppCompatActivity {
    EditText editText;
    RecyclerView recyclerView;
    MyDatabaseHelper db;
    ArrayList<String>  IME, MAX;
    CustomAdapter customAdapter;
    EditText nadjiImeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filteri);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        recyclerView = findViewById(R.id.lista);
        db = new MyDatabaseHelper(Filteri.this);
        IME = new ArrayList<>();
        MAX = new ArrayList<>();
        stavi_u_String();
        customAdapter = new CustomAdapter(Filteri.this,IME,MAX);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Filteri.this));
        nadjiImeEditText = findViewById(R.id.nadjiIme);
        nadjiImeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
                MyDatabaseHelper db = new MyDatabaseHelper(Filteri.this);
                Cursor c = db.nadji_Ime(searchText);
                IME = new ArrayList<>();
                MAX = new ArrayList<>();
                if(c.getCount() == 0){
                   return;
                }
                while (c.moveToNext()){
                    IME.add(c.getString(0));
                    MAX.add(c.getString(1));
                }
                customAdapter = new CustomAdapter(Filteri.this,IME,MAX);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Filteri.this));

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void stavi_u_String(){
        Cursor cursor = db.vrati_sve("DESC");
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()){
            IME.add(cursor.getString(0));
            MAX.add(cursor.getString(1));
        }
    }
    public void filter(View view){
        Button  dugme = findViewById(R.id.filterpoSkoru);
        String tekst = dugme.getText().toString().trim();
        if(tekst.equals("Najbolji")){
            dugme.setText("Najgori");
            recyclerView = findViewById(R.id.lista);
            db = new MyDatabaseHelper(Filteri.this);
            IME = new ArrayList<>();
            MAX = new ArrayList<>();
            Cursor cursor = db.vrati_sve("DESC");
            if(cursor.getCount() == 0){
                Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
            }
            while (cursor.moveToNext()){
                IME.add(cursor.getString(0));
                MAX.add(cursor.getString(1));
            }
            customAdapter = new CustomAdapter(Filteri.this, IME,MAX);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Filteri.this));


        }else {
            dugme.setText("Najbolji");
            recyclerView = findViewById(R.id.lista);
            db = new MyDatabaseHelper(Filteri.this);
            IME = new ArrayList<>();
            MAX = new ArrayList<>();
            Cursor cursor = db.vrati_sve("ASC");
            if(cursor.getCount() == 0){
                Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
            }
            while (cursor.moveToNext()){
                IME.add(cursor.getString(0));
                MAX.add(cursor.getString(1));
            }
            customAdapter = new CustomAdapter(Filteri.this,IME,MAX);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(Filteri.this));
        }
    }





}