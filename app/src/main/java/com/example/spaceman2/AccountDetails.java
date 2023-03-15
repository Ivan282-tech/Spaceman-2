package com.example.spaceman2;
import static com.example.spaceman2.Login.ime;
import static com.example.spaceman2.Login.id;
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

public class AccountDetails extends AppCompatActivity {
    String ime2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        VideoView videoView = findViewById(R.id.fullScreenVideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pozadina1;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        EditText Ime = findViewById(R.id.Ime);
        ime2 = Ime.getText().toString().trim();
        EditText Sifra = findViewById(R.id.sifra);
        EditText Potvrda = findViewById(R.id.sifra2);
        MyDatabaseHelper db = new MyDatabaseHelper(AccountDetails.this);
        db.vratiSifru(id);
        Ime.setText(ime);
        Sifra.setText(db.vratiSifru(id));
        Potvrda.setText(db.vratiSifru(id));
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void updateIgraca(View view) {
        MyDatabaseHelper baza = new MyDatabaseHelper(AccountDetails.this);
        EditText Ime = findViewById(R.id.Ime);
        String IME = Ime.getText().toString().trim();
        EditText Sifra = findViewById(R.id.sifra);
        String sifra = Sifra.getText().toString().trim();
        EditText Potvrda = findViewById(R.id.sifra2);
        String potvrda = Potvrda.getText().toString().trim();
        if(sifra.length() > 6){
            if(sifra.equals(potvrda)){
                if(ime.equals(IME)){
                    baza.upisiNovePodatke(id, Ime.getText().toString().trim(), Sifra.getText().toString().trim());
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    if(baza.proveriIme(IME)) {
                        Toast.makeText(this, "User with this name already exists", Toast.LENGTH_SHORT).show();
                    }else {
                        baza.upisiNovePodatke(id, Ime.getText().toString().trim(), Sifra.getText().toString().trim());
                        Intent intent = new Intent(this, Login.class);
                        startActivity(intent);
                        this.finish();
                    }
                }
            }else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
        }



    }

    public void obrisiIgraca(View view) {
        MyDatabaseHelper baza = new MyDatabaseHelper(AccountDetails.this);
        baza.obrisiIgraca(id);
        Toast.makeText(this, "User has been deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }
}