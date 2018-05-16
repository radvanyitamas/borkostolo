package com.example.olahgabormihaly.borkostolo_hits.database.model;

public class Bor {

    public static final String TABLE_NAME = "borok";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GYARTO = "BorGyarto";
    public static final String COLUMN_NEVE = "BorNeve";
    public static final String COLUMN_FAJTA = "BorFajta";
    public static final String COLUMN_SZIN = "BorSzin";
    public static final String COLUMN_EVJARAT = "BorEvjarat";
    public static final String COLUMN_ALKOHOLTARTAM = "BorAlkhoholTartam";
    public static final String COLUMN_FOGYASZTASIHOMERSEKLET = "BorFogyasztaiHomerseklet";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_GYARTO + " TEXT,"
            + COLUMN_NEVE + " TEXT,"
            + COLUMN_FAJTA + " TEXT,"
            + COLUMN_SZIN + " TEXT,"
            + COLUMN_EVJARAT + " INTEGER,"
            + COLUMN_ALKOHOLTARTAM + " REAL,"
            + COLUMN_FOGYASZTASIHOMERSEKLET + " INTEGER"
            + ")";

    private int ID;
    private String BorGyarto;
    private String BorNeve;
    private String BorFajta;
    private String BorSzin;
    private int BorEvjarat;
    private float BorAlkohol_tartam;
    private int BorFogyasztasiHomerseklet;

    public Bor() {}

    public Bor(int id, String gyarto, String neve, String fajta, String szin, int evjarat, float alkoholTartam, int fogyasztasiHomerseklet) {
        this.ID = id;
        this.BorGyarto = gyarto;
        this.BorNeve = neve;
        this.BorFajta = fajta;
        this.BorSzin = szin;
        this.BorEvjarat = evjarat;
        this.BorAlkohol_tartam = alkoholTartam;
        this.BorFogyasztasiHomerseklet = fogyasztasiHomerseklet;
    }


    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getBorGyarto() {
        return BorGyarto;
    }

    public void setBorGyarto(String borGyarto) {
        BorGyarto = borGyarto;
    }

    public String getBorNeve() {
        return BorNeve;
    }

    public void setBorNeve(String borNeve) {
        BorNeve = borNeve;
    }

    public String getBorFajta() {
        return BorFajta;
    }

    public void setBorFajta(String borFajta) {
        BorFajta = borFajta;
    }

    public String getBorSzin() {
        return BorSzin;
    }

    public void setBorSzin(String borSzin) {
        BorSzin = borSzin;
    }

    public int getBorEvjarat() {
        return BorEvjarat;
    }

    public void setBorEvjarat(int borEvjarat) {
        BorEvjarat = borEvjarat;
    }

    public float getBorAlkohol_tartam() {
        return BorAlkohol_tartam;
    }

    public void setBorAlkohol_tartam(float borAlkohol_tartam) {
        BorAlkohol_tartam = borAlkohol_tartam;
    }

    public int getBorFogyasztasiHomerseklet() {
        return BorFogyasztasiHomerseklet;
    }

    public void setBorFogyasztasiHomerseklet(int borFogyasztasiHomerseklet) {
        BorFogyasztasiHomerseklet = borFogyasztasiHomerseklet;
    }
}
