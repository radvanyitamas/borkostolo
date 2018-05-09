package com.example.olahgabormihaly.borkostolo_hits.database.model;

public class Borbiralat {

    public final static String TABLE_NAME = "Borbiralat";

    public final static String COLUMN_ID = "ID";
    public final static String COLUMN_BiraltBorID = "BiraltBorID";
    public final static String COLUMN_BiraloSzemelyID = "BiraloSzemelyID";
    public final static String COLUMN_MegjelenesTisztasag = "MegjelenesTisztasag";
    public final static String COLUMN_MegjelentesSzin = "MegjelenesSzin";
    public final static String COLUMN_IllatIntenzitas = "IllatIntenzitas";
    public final static String COLUMN_IllatKarakter = "IllatKarakter";
    public final static String COLUMN_IllatMinoseg = "IllatMinoseg";
    public final static String COLUMN_ZamatIntenzitas = "ZamatInzenzitas";
    public final static String COLUMN_ZamatKarakter = "ZamatKarakter";
    public final static String COLUMN_ZamatMinoseg = "ZamatMinoseg";
    public final static String COLUMN_ZamatHosszusag = "ZamatHosszusag";
    public final static String COLUMN_Osszbenyomas = "ZamatOsszbenyomas";
    public final static String COLUMN_Kizarva = "Kizarva";
    public final static String COLUMN_Osszesen = "Osszesen";
    public final static String COLUMN_MegjegyzesMegjelenesTisztasag = "MegjegyzesMegjelenesTisztasag";
    public final static String COLUMN_MegjegyzesMegjelenesSzin = "MegjegyzesMegjelenesSzin";
    public final static String COLUMN_MegjegyzesIllatIntenzitas = "MegjegyzesIllatIntenzitas";
    public final static String COLUMN_MegjegyzesIllatKarakter = "MegjegyzesIllatKarakter";
    public final static String COLUMN_MegjegyzesIllatMinoseg = "MegjegyzesIllatMinoseg";
    public final static String COLUMN_MegjegyzesZamatIntezitas = "MegjegyzesZamatIntenzitas";
    public final static String COLUMN_MegjegyzesZamatKarakter = "MegjegyzesZamatKarakter";
    public final static String COLUMN_MegjegyzesZamatMinoseg = "MegjegyzesZamatMinoseg";
    public final static String COLUMN_MegjegyzesZamatHosszusag = "MegjegyzesZamatHosszusag";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BiraltBorID + " INTEGER,"
                    + COLUMN_BiraloSzemelyID + " INTEGER,"
                    + COLUMN_MegjelenesTisztasag + " INTEGER,"
                    + COLUMN_MegjelentesSzin + " INTEGER,"
                    + COLUMN_IllatIntenzitas + " INTEGER,"
                    + COLUMN_IllatKarakter + " INTEGER,"
                    + COLUMN_IllatMinoseg + " INTEGER,"
                    + COLUMN_ZamatIntenzitas + " INTEGER,"
                    + COLUMN_ZamatKarakter + " INTEGER,"
                    + COLUMN_ZamatMinoseg + " INTEGER,"
                    + COLUMN_ZamatHosszusag + " INTEGER,"
                    + COLUMN_Osszbenyomas + " INTEGER,"
                    + COLUMN_Kizarva + " TEXT,"
                    + COLUMN_Osszesen + " INTEGER,"
                    + COLUMN_MegjegyzesMegjelenesTisztasag + " TEXT,"
                    + COLUMN_MegjegyzesMegjelenesSzin + " TEXT,"
                    + COLUMN_MegjegyzesIllatIntenzitas + " TEXT,"
                    + COLUMN_MegjegyzesIllatKarakter + " TEXT,"
                    + COLUMN_MegjegyzesIllatMinoseg + " TEXT,"
                    + COLUMN_MegjegyzesZamatIntezitas + " TEXT,"
                    + COLUMN_MegjegyzesZamatKarakter + " TEXT,"
                    + COLUMN_MegjegyzesZamatMinoseg + " TEXT,"
                    + COLUMN_MegjegyzesZamatHosszusag + " TEXT," +
                    "FOREIGN KEY(BiraltBorID) REFERENCES borok(id)," +
                    "FOREIGN KEY(BiraloSzemelyID) REFERENCES borkostolo_szemelyek(id)"
                    + ")";


    private int id;
    private int biraltBorID;
    private int biraloSzemelyID;
    private int megjelenesTisztasag;
    private int megjelentesSzin;
    private int illatIntenzitas;
    private int illatKarakter;
    private int illatMinoseg;
    private int zamatIntenzitas;
    private int zamatKarakter;
    private int zamatMinoseg;
    private int zamatHosszusag;
    private int osszbenyomas;
    private String kizarva;
    private int osszesen;
    private String megjegyzesMegjelenesTisztasag;
    private String megjegyzesMegjelenesSzin;
    private String megjegyzesIllatIntenzitas;
    private String megjegyzesIllatKarakter;
    private String megjegyzesIllatMinoseg;
    private String megjegyzesZamatIntezitas;
    private String megjegyzesZamatKarakter;
    private String megjegyzesZamatMinoseg;
    private String megjegyzesZamatHosszusag;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBiraltBorID() {
        return biraltBorID;
    }

    public void setBiraltBorID(int biraltBorID) {
        this.biraltBorID = biraltBorID;
    }

    public int getBiraloSzemelyID() {
        return biraloSzemelyID;
    }

    public void setBiraloSzemelyID(int biraloSzemelyID) {
        this.biraloSzemelyID = biraloSzemelyID;
    }

    public int getMegjelenesTisztasag() {
        return megjelenesTisztasag;
    }

    public void setMegjelenesTisztasag(int megjelenesTisztasag) {
        this.megjelenesTisztasag = megjelenesTisztasag;
    }

    public int getMegjelentesSzin() {
        return megjelentesSzin;
    }

    public void setMegjelentesSzin(int megjelentesSzin) {
        this.megjelentesSzin = megjelentesSzin;
    }

    public int getIllatIntenzitas() {
        return illatIntenzitas;
    }

    public void setIllatIntenzitas(int illatIntenzitas) {
        this.illatIntenzitas = illatIntenzitas;
    }

    public int getIllatKarakter() {
        return illatKarakter;
    }

    public void setIllatKarakter(int illatKarakter) {
        this.illatKarakter = illatKarakter;
    }

    public int getIllatMinoseg() {
        return illatMinoseg;
    }

    public void setIllatMinoseg(int illatMinoseg) {
        this.illatMinoseg = illatMinoseg;
    }

    public int getZamatIntenzitas() {
        return zamatIntenzitas;
    }

    public void setZamatIntenzitas(int zamatIntenzitas) {
        this.zamatIntenzitas = zamatIntenzitas;
    }

    public int getZamatKarakter() {
        return zamatKarakter;
    }

    public void setZamatKarakter(int zamatKarakter) {
        this.zamatKarakter = zamatKarakter;
    }

    public int getZamatMinoseg() {
        return zamatMinoseg;
    }

    public void setZamatMinoseg(int zamatMinoseg) {
        this.zamatMinoseg = zamatMinoseg;
    }

    public int getZamatHosszusag() {
        return zamatHosszusag;
    }

    public void setZamatHosszusag(int zamatHosszusag) {
        this.zamatHosszusag = zamatHosszusag;
    }

    public int getOsszbenyomas() {
        return osszbenyomas;
    }

    public void setOsszbenyomas(int osszbenyomas) {
        this.osszbenyomas = osszbenyomas;
    }

    public String getKizarva() {
        return kizarva;
    }

    public void setKizarva(String kizarva) {
        this.kizarva = kizarva;
    }

    public int getOsszesen() {
        return osszesen;
    }

    public void setOsszesen(int osszesen) {
        this.osszesen = osszesen;
    }

    public String getMegjegyzesMegjelenesTisztasag() {
        return megjegyzesMegjelenesTisztasag;
    }

    public void setMegjegyzesMegjelenesTisztasag(String megjegyzesMegjelenesTisztasag) {
        this.megjegyzesMegjelenesTisztasag = megjegyzesMegjelenesTisztasag;
    }

    public String getMegjegyzesMegjelenesSzin() {
        return megjegyzesMegjelenesSzin;
    }

    public void setMegjegyzesMegjelenesSzin(String megjegyzesMegjelenesSzin) {
        this.megjegyzesMegjelenesSzin = megjegyzesMegjelenesSzin;
    }

    public String getMegjegyzesIllatIntenzitas() {
        return megjegyzesIllatIntenzitas;
    }

    public void setMegjegyzesIllatIntenzitas(String megjegyzesIllatIntenzitas) {
        this.megjegyzesIllatIntenzitas = megjegyzesIllatIntenzitas;
    }

    public String getMegjegyzesIllatKarakter() {
        return megjegyzesIllatKarakter;
    }

    public void setMegjegyzesIllatKarakter(String megjegyzesIllatKarakter) {
        this.megjegyzesIllatKarakter = megjegyzesIllatKarakter;
    }

    public String getMegjegyzesIllatMinoseg() {
        return megjegyzesIllatMinoseg;
    }

    public void setMegjegyzesIllatMinoseg(String megjegyzesIllatMinoseg) {
        this.megjegyzesIllatMinoseg = megjegyzesIllatMinoseg;
    }

    public String getMegjegyzesZamatIntezitas() {
        return megjegyzesZamatIntezitas;
    }

    public void setMegjegyzesZamatIntezitas(String megjegyzesZamatIntezitas) {
        this.megjegyzesZamatIntezitas = megjegyzesZamatIntezitas;
    }

    public String getMegjegyzesZamatKarakter() {
        return megjegyzesZamatKarakter;
    }

    public void setMegjegyzesZamatKarakter(String megjegyzesZamatKarakter) {
        this.megjegyzesZamatKarakter = megjegyzesZamatKarakter;
    }

    public String getMegjegyzesZamatMinoseg() {
        return megjegyzesZamatMinoseg;
    }

    public void setMegjegyzesZamatMinoseg(String megjegyzesZamatMinoseg) {
        this.megjegyzesZamatMinoseg = megjegyzesZamatMinoseg;
    }

    public String getMegjegyzesZamatHosszusag() {
        return megjegyzesZamatHosszusag;
    }

    public void setMegjegyzesZamatHosszusag(String megjegyzesZamatHosszusag) {
        this.megjegyzesZamatHosszusag = megjegyzesZamatHosszusag;
    }

}
