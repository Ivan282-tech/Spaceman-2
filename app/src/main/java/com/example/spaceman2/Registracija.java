package com.example.spaceman2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class Registracija extends AppCompatActivity {



    private EditText ime;
    private EditText sifra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        VideoView videoView = findViewById(R.id.fullScreenVideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pozadina1;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }

    public void dodajIgraca(View view) {
            ime = findViewById(R.id.Ime);
            sifra = findViewById(R.id.Sifra);
            EditText potvrda = findViewById(R.id.Potvrda);
            String Ime = ime.getText().toString().trim();
            String SIFRA1 = sifra.getText().toString().trim();
            String SIFRA2 = potvrda.getText().toString().trim();
            MyDatabaseHelper baza = new MyDatabaseHelper(Registracija.this);
            if(SIFRA1.equals(SIFRA2) && baza.proveriIme(Ime) == false){
                baza.dodajIgraca(ime.getText().toString().trim(), sifra.getText().toString().trim(), 0);
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                this.finish();
            } else{
                Toast.makeText(this, "Password do not match or name already exists", Toast.LENGTH_SHORT).show();
                sifra.getText().clear();
                potvrda.getText().clear();
            }
    }
}