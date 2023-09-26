package com.example.spaceman2;

import static com.example.spaceman2.Login.id;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String IMEBAZE = "Igraci.db";
    private static final int VERZIJABAZE = 1;
    private static final String IMETABELE = "Igrac";
    private static final String ID = "_id";
    private static final String IME = "ime";
    private static final String SIFRA = "sifra";
    private static final String MAXSKOR = "maxSkor";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, IMEBAZE,null, VERZIJABAZE);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String query = "CREATE TABLE " + IMETABELE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    IME + " TEXT, " +
                    SIFRA + " TEXT, " +
                    MAXSKOR + " INTEGER);";
        sqLiteDatabase.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IMETABELE);
        onCreate(sqLiteDatabase);
    }
    void dodajIgraca (String ime,  String sifra, int maxSkor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IME, ime);
        cv.put(SIFRA, sifra);
        cv.put(MAXSKOR, maxSkor);
        long rezultat = db.insert(IMETABELE, null,cv);
        if(rezultat == -1)
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    boolean login(String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Igrac WHERE sifra = " + "'" + sifra + "'" + " AND ime = " + "'" + ime + "'", null);
        if(c.getCount() == 0)
        {
            return false;
        }
        else {
            return true;
        }

    }
    int vratiNajbolji(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT maxSkor FROM Igrac WHERE _id = " + "'" + id + "'", null);
        c.moveToFirst();
        return c.getInt(0);
    }
    void upisiNajbolji(int broj, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "UPDATE Igrac SET maxSkor = " + broj + " WHERE _id = " + "'" + id + "'";
        db.execSQL(command);
    }
    String vratiSifru(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT sifra FROM Igrac WHERE _id = " + "'" + id + "'", null);
        c.moveToFirst();
        return c.getString(0);
    }

    void upisiNovePodatke(String id, String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "UPDATE Igrac SET ime = " + "'" + ime + "'" + ", sifra = " +"'" + sifra + "'"  + " WHERE _id = "   + id;
        db.execSQL(command);
    }
    String vratiID(String ime, String sifra)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT _id FROM Igrac WHERE sifra = " + "'" + sifra + "'" + " AND ime = " + "'" + ime + "'", null);
        c.moveToFirst();
        return c.getString(0);
    }
    void obrisiIgraca(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String command = "DELETE FROM Igrac WHERE _id = " + id;
        db.execSQL(command);
    }
    boolean proveriIme(String ime){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT ime FROM Igrac WHERE ime = " + "'" + ime + "'" , null);
        if(c.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    Cursor vrati_sve(String k) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery("SELECT  ime, maxSkor FROM Igrac ORDER BY maxSkor " + k  , null);
        }
        return c;
    }

    Cursor nadji_Ime(String ime){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT ime, maxSkor FROM Igrac WHERE ime LIKE '%" + ime + "%'", null);
        if(c.getCount() > 0){
            return c;
        }
        return c;
    }










}
