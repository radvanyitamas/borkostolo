package com.example.olahgabormihaly.borkostolo_hits.database.model;

public class BorkostoloSzemely {
    public static final String TABLE_NAME = "borkostolo_szemelyek";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SZEM_IG_SZAM = "SzemIgSzam";
    public static final String COLUMN_VNEV = "VezetekNev";
    public static final String COLUMN_KNEV = "KeresztNev";
    public static final String COLUMN_SZULDATUM = "SzuletesiDatum";
    public static final String COLUMN_SZAKMAISAG = "SzakmaisagiErteke";
    public static final String COLUMN_TIMESTAMP = "Timestamp";

    private int ID;
    private String SzemIgSzam;
    private String VezetekNev;
    private String KeresztNev;
    private String SzuletesiDatum;
    private int SzakmaisagiErtek;
    private long Timestamp;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SZEM_IG_SZAM + " TEXT,"
            + COLUMN_VNEV + " TEXT,"
            + COLUMN_KNEV + " TEXT,"
            + COLUMN_SZULDATUM + " TEXT,"
            + COLUMN_SZAKMAISAG + " INTEGER,"
            + COLUMN_TIMESTAMP + " INTEGER"
            + ")";

    public BorkostoloSzemely() {
    }

    public BorkostoloSzemely(int id, String szemIgSzam, String vezetekNev, String keresztNev, String szuletesiDatum, int szakmaisagiErtek, String timestamp) {
        this.ID = id;
        this.SzemIgSzam = szemIgSzam;
        this.VezetekNev = vezetekNev;
        this.KeresztNev = keresztNev;
        this.SzuletesiDatum = szuletesiDatum;
        this.SzakmaisagiErtek = szakmaisagiErtek;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getSzemIgSzam() {
        return SzemIgSzam;
    }

    public void setSzemIgSzam(String szemIgSzam) {
        this.SzemIgSzam = szemIgSzam;
    }

    public String getVezetekNev() {
        return VezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.VezetekNev = vezetekNev;
    }

    public String getKeresztNev() {
        return KeresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.KeresztNev = keresztNev;
    }

    public String getSzuletesiDatum() {
        return SzuletesiDatum;
    }

    public void setSzuletesiDatum(String szuletesiDatum) {
        this.SzuletesiDatum = szuletesiDatum;
    }

    public int getSzakmaisagiErtek() {
        return SzakmaisagiErtek;
    }

    public void setSzakmaisagiErtek(int szakmaisagiErtek) {
        this.SzakmaisagiErtek = szakmaisagiErtek;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.Timestamp = timestamp;
    }

}
