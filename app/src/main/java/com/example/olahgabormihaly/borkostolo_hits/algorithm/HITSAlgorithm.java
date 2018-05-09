package com.example.olahgabormihaly.borkostolo_hits.algorithm;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class HITSAlgorithm implements Callable<List<Float>> {

    /*
     * x -> kóstoló
     *
     * y -> bor
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

    private List<Float> kostolok;
    private List<Float> borok;

    private float[][] Wx;

    public HITSAlgorithm(List<Float> kostolok, List<Float> borok) {
        this.kostolok = kostolok;
        this.borok = borok;
    }

    @Override
    public List<Float> call() throws Exception {
        Wx = new float[kostolok.size()][kostolok.size()];
        float[] rangsor = new float[kostolok.size()];

        for (int i = 0; i < kostolok.size(); i++) {
            for (int j = 0; j < kostolok.size(); j++) {
                for (int k = 0; k < borok.size(); k++) {
                    Wx[i][j] += getKostoloBorSuly(i, k) * getBorKostoloSuly(j, k);
                }
            }
        }

        //Mátrix szorzás (Wx*p), ahol p a kostolók vektora és mindegyik kezdőértéke 1
        for (int i = 0; i < kostolok.size(); i++) {
            rangsor[i] = 0;
            for (int j = 0; j < kostolok.size(); j++) {
                rangsor[i] += Wx[i][j];
            }
        }

        List<Float> list = new ArrayList<>();
        for(int i = 0; i < kostolok.size(); i++) {
            list.add(rangsor[i]);
        }
        return list;
    }

    //w(xi yj)vissza
    // Bortól a kostoló felé mutató él normalizált értéke
    private float getBorKostoloSuly(long kostoloPosition, long borPosition) {
        float suly = 0;

        suly = Math.abs(getD(borPosition) - Math.abs(getBorAtlagPontszam(borPosition) - getPontszam(kostoloPosition, borPosition))) / (kostolok.size() - 1) * getD(borPosition);

        return suly;
    }

    //D
    private float getD(long borPosition) {
        float osszeg = 0;

        for (int i = 0; i < kostolok.size(); i++) {
            osszeg += Math.abs(getBorAtlagPontszam(borPosition) - getPontszam(i, borPosition));
        }

        return osszeg;
    }

    //w(xi yj)oda
    private float getKostoloBorSuly(long kostoloPosition, long borPosition) {
        float suly = 0;

        float osszeg = 0;

        for (int i = 0; i < borok.size(); i++) {
            Float bor = borok.get(i);

            osszeg += getPontszam(kostoloPosition, i);
        }

        suly = getPontszam(kostoloPosition, borPosition) / osszeg;

        return suly;
    }

    private float getBorAtlagPontszam(long borPosition) {
        float pontszam = 0;

        for (int i = 0; i < kostolok.size(); i++) {
            pontszam += getPontszam(i, borPosition);
        }

        return pontszam / kostolok.size();
    }

    private int getPontszam(long kostoloPosition, long borPosition) {
        // position -> adatbázisból kiszeded az összes kóstolót, és a listának az eleme
        return (kostoloPosition % 2 == 0) ? 50 : (borPosition % 2 == 0) ? 40 : 55 ; //todo adatbázisból kiszedni az adott kóstoló adott borra adott összpontszámát (köztes tábla)
    }
}
