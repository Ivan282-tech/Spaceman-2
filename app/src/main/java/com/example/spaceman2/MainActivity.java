package com.example.spaceman2;



import static com.example.spaceman2.Login.ime;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sakrij_bar();
        VideoView videoView = findViewById(R.id.fullScreenVideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pozadina1;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    private void sakrij_bar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }

    public void Account(View view) {
        Intent intent = new Intent(this, AccountDetails.class);
        startActivity(intent);
        this.finish();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }

}

