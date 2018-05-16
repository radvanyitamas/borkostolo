package com.example.olahgabormihaly.borkostolo_hits.algorithm;


import android.content.Context;

import com.example.olahgabormihaly.borkostolo_hits.database.DatabaseHelper;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Borbiralat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class HITSAlgorithm implements Callable<List<Float>> {

    /*
     * x -> kóstoló
     *
     * y -> bor
     *
     * kóstoló kezdőérték = 1
     *
     * bor kezdőérték = borra kapott pontok átlaga
     *
     * w(xi yj)oda = w(xi yj)oda vessző / szumma(j eleme boroknak) w(xi yj)oda vessző
     *
     * D = szumma(i eleme kostolóknak) (bor kezdőérték j - w(xi yj)oda vessző)abs
     *
     * w(xi yj)vissza = ( D - (bor kezdőérték - (w(xi yj)oda vessző )abs )abs / ( (kóstolók száma -1) * D )
     *
     * w(xi yk)oda = ahány pontot a kóstoló adott a borra (normalizálva)
     *
     * Wx[i][j] = szumma(k eleme boroknak) w(xi yk)oda * w(xj yk)vissza
     *
     * p = Wx mátrix * p vektor
     *
     * */

    private static final int KOSTOLO_KEZDOERTEK = 1;

    private Context context;
    private int kostolokSzama;
    private List<Float> borok;

    public HITSAlgorithm(Context context, int kostolokSzama, List<Float> borok) {
        this.context = context;
        this.kostolokSzama = kostolokSzama;
        this.borok = borok;
    }

    @Override
    public List<Float> call() {
        float[][] wx = new float[kostolokSzama][kostolokSzama];

        for (int i = 0; i < kostolokSzama; i++) {
            for (int j = 0; j < kostolokSzama; j++) {
                for (int k = 0; k < borok.size(); k++) {
                    float kostoloBorSuly = getKostoloBorSuly(i, k);
                    float borKostoloSuly = getBorKostoloSuly(j, k);
                    wx[i][j] += kostoloBorSuly * borKostoloSuly;
                }
            }
        }

        //HITS-egyenletek megoldása
        List<Float> rangsor = new ArrayList<>();
        float szumma = 0;
        for (int j = 0; j < kostolokSzama; j++) {
            szumma += wx[j][0] * KOSTOLO_KEZDOERTEK;
        }
        rangsor.add(szumma);
        szumma = 0;
        for (int j = 0; j < kostolokSzama; j++) {
            szumma += wx[j][1] * KOSTOLO_KEZDOERTEK;
        }
        rangsor.add(szumma);
        szumma = 0;
        for (int j = 0; j < kostolokSzama; j++) {
            szumma += wx[j][2] * KOSTOLO_KEZDOERTEK;
        }
        rangsor.add(szumma);
        return rangsor;
    }

    //w(xi yj)vissza
    // Bortól a kostoló felé mutató él normalizált értéke
    private float getBorKostoloSuly(int kostoloPosition, int borPosition) {

        // | D - | qj0 - w(xi yj) oda vessző | |
        float szamlalo = Math.abs(getD(borPosition) - Math.abs(getBorAtlagPontszam(borPosition) - getPontszam(kostoloPosition, borPosition)));

        // (L-1)*D
        float nevezo = (kostolokSzama - 1) * getD(borPosition);

        return szamlalo / nevezo;
    }

    //D = szumma i | qj0 - w(xi yj)vessző oda |
    private float getD(int borPosition) {

        float osszeg = 0;
        for (int i = 0; i < kostolokSzama; i++) {
            osszeg += Math.abs(getBorAtlagPontszam(borPosition) - getPontszam(i, borPosition));
        }

        return osszeg;
    }

    //w(xi yj)oda
    private float getKostoloBorSuly(int kostoloPosition, int borPosition) {

        float osszeg = 0;
        for (int i = 0; i < borok.size(); i++) {
            osszeg += getPontszam(kostoloPosition, i);
        }

        return getPontszam(kostoloPosition, borPosition) / osszeg;
    }

    private float getBorAtlagPontszam(long borPosition) {
        return borok.get((int) borPosition);
    }

    private float getPontszam(int kostoloPosition, int borPosition) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        List<Borbiralat> allBorBiralat = databaseHelper.getAllBorBiralat();

        //A lista indexelése 0-tól és az adatbázisban 1-től
        //Listában pozíciót és ID-t azért hasonlíthatunk össze, mert az adatbázisból ID szerint növekvő sorrendben olvassuk ki az adatokat
        for (Borbiralat borbiralat : allBorBiralat) {
            if (borbiralat.getBiraloSzemelyID() - 1 == kostoloPosition && borbiralat.getBiraltBorID() - 1 == borPosition) {
                return borbiralat.getOsszesen();
            }
        }
        return 0;
    }
}
