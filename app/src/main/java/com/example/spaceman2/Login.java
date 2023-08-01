package com.example.spaceman2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class Login extends AppCompatActivity {
    public static String ime;
    public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
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
    public void Registracija(View view) {
        Intent intent = new Intent(this, Registracija.class);
        startActivity(intent);
        this.finish();
    }

   public void login(View view) {
       MyDatabaseHelper baza = new MyDatabaseHelper(Login.this);
       EditText ime = findViewById(R.id.ImeLogin);
       EditText sifra = findViewById(R.id.SifraLogin);
       this.ime = ime.getText().toString().trim();
       Log.d("INFO", this.ime);
       if(baza.login(ime.getText().toString().trim(), sifra.getText().toString().trim()))
       {
           id = baza.vratiID(ime.getText().toString().trim(),sifra.getText().toString().trim());
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);
           this.finish();
       }
       else{
           Toast.makeText(this, "Login has not been authorized", Toast.LENGTH_SHORT).show();
       }
   }

}