package com.example.spaceman2;

public class Rezultat {
    private float A;
    private float B;
    private String sifra;
    public Rezultat(float A, float B){
        this.A = A;
        this.B = B;
    }
    public Rezultat(String sifra){
        this.sifra = sifra;
    }

    public float getA()
    {
        return A;
    }
    public float getB()
    {
        return B;
    }


}
