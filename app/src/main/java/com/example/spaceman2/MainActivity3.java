
package com.example.spaceman2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.spaceman2.Login.id;
import static com.example.spaceman2.Login.ime;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;
import static com.example.spaceman2.MainActivity2.sekunde;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        VideoView videoView = findViewById(R.id.fullScreenVideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pozadina3;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        TextView gejm_over = findViewById(R.id.game_over);
        gejm_over.setText("GAME OVER");
        MyDatabaseHelper baza = new MyDatabaseHelper(MainActivity3.this);
        int najbolji = baza.vratiNajbolji(id);
        TextView naj = findViewById(R.id.najbolji);
        TextView zapravo_skor = findViewById(R.id.zapravo_skor);
        zapravo_skor.setText(Integer.toString(sekunde));
        if(sekunde > najbolji){
            baza.upisiNajbolji(sekunde, id);
            naj.setText(Integer.toString(sekunde));
        }
        else{
            naj.setText(Integer.toString(najbolji));
        }
    }
    public void restart(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
    public void pocetna(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void filter(View view){
        Intent intent = new Intent(this, Filteri.class);
        startActivity(intent);
        finish();
    }
}
