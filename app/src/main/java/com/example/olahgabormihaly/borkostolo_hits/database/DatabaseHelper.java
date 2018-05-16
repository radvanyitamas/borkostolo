package com.example.olahgabormihaly.borkostolo_hits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.olahgabormihaly.borkostolo_hits.database.model.Bor;
import com.example.olahgabormihaly.borkostolo_hits.database.model.Borbiralat;
import com.example.olahgabormihaly.borkostolo_hits.database.model.BorkostoloSzemely;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 9;

    // Database Name
    private static final String DATABASE_NAME = "COHITS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create notes table
        db.execSQL(BorkostoloSzemely.CREATE_TABLE);
        db.execSQL(Bor.CREATE_TABLE);
        db.execSQL(Borbiralat.CREATE_TABLE);

        long kostolo1 = insertKostolo("2345", "Üveges", "Kázmér", "", 0, db);
        long kostolo2 = insertKostolo("3456", "Robusztus", "Károly", "", 0, db);
        long kostolo3 = insertKostolo("1234", "Nagy", "Lajos", "", 0, db);

        long bor1 = insertBor("", "Kékfrankos", "", "", 2000, 12, 0, db);
        long bor2 = insertBor("", "Gléda", "", "", 2000, 9, 0, db);
        long bor3 = insertBor("", "Mulató$", "", "", 2000, 10, 0, db);

        insertBorBiralat((int) bor1, (int) kostolo1, 86, db);
        insertBorBiralat((int) bor1, (int) kostolo2, 87, db);
        insertBorBiralat((int) bor1, (int) kostolo3, 65, db);
        insertBorBiralat((int) bor2, (int) kostolo1, 76, db);
        insertBorBiralat((int) bor2, (int) kostolo2, 89, db);
        insertBorBiralat((int) bor2, (int) kostolo3, 67, db);
        insertBorBiralat((int) bor3, (int) kostolo1, 88, db);
        insertBorBiralat((int) bor3, (int) kostolo2, 92, db);
        insertBorBiralat((int) bor3, (int) kostolo3, 95, db);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if extisted
        db.execSQL("DROP TABLE IF EXISTS " + BorkostoloSzemely.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Bor.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Borbiralat.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertKostolo(String szemSzam, String vNev, String kNev, String szulDatum, long timeStamp) {
        // Get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        long id = insertKostolo(szemSzam, vNev, kNev, szulDatum, timeStamp, db);

        // Close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    private long insertKostolo(String szemSzam, String vNev, String kNev, String szulDatum, long timeStamp, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // 'id' and 'timestamp' will be inserted automatically
        // no need to add them
        values.put(BorkostoloSzemely.COLUMN_SZAKMAISAG, 1.0);
        values.put(BorkostoloSzemely.COLUMN_SZULDATUM, szulDatum);
        values.put(BorkostoloSzemely.COLUMN_KNEV, kNev);
        values.put(BorkostoloSzemely.COLUMN_VNEV, vNev);
        values.put(BorkostoloSzemely.COLUMN_SZEM_IG_SZAM, szemSzam);
        values.put(BorkostoloSzemely.COLUMN_TIMESTAMP, timeStamp);

        // Insert row
        return db.insert(BorkostoloSzemely.TABLE_NAME, null, values);
    }

    public BorkostoloSzemely getKostolo(long id) {
        // Get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(BorkostoloSzemely.TABLE_NAME,
                new String[]{
                        BorkostoloSzemely.COLUMN_ID,
                        BorkostoloSzemely.COLUMN_SZEM_IG_SZAM,
                        BorkostoloSzemely.COLUMN_VNEV,
                        BorkostoloSzemely.COLUMN_KNEV,
                        BorkostoloSzemely.COLUMN_SZULDATUM,
                        BorkostoloSzemely.COLUMN_SZAKMAISAG,
                        BorkostoloSzemely.COLUMN_TIMESTAMP},
                BorkostoloSzemely.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // Prepare borkostoloSzemely object
        BorkostoloSzemely borkostoloSzemely = new BorkostoloSzemely(
                cursor.getInt(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZEM_IG_SZAM)),
                cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_VNEV)),
                cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_KNEV)),
                cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZULDATUM)),
                cursor.getInt(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZAKMAISAG)),
                cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_TIMESTAMP))
        );

        // Close the db connection
        cursor.close();

        return borkostoloSzemely;
    }

    public List<BorkostoloSzemely> getAllKostolo() {
        List<BorkostoloSzemely> borkostoloSzemely = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + BorkostoloSzemely.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BorkostoloSzemely borkostoloSzemelyek = new BorkostoloSzemely();
                borkostoloSzemelyek.setId((cursor.getInt(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_ID))));
                borkostoloSzemelyek.setSzemIgSzam(cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZEM_IG_SZAM)));
                borkostoloSzemelyek.setVezetekNev(cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_VNEV)));
                borkostoloSzemelyek.setKeresztNev(cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_KNEV)));
                borkostoloSzemelyek.setSzuletesiDatum(cursor.getString(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZULDATUM)));
                borkostoloSzemelyek.setSzakmaisagiErtek(cursor.getInt(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_SZAKMAISAG)));
                borkostoloSzemelyek.setTimestamp(cursor.getLong(cursor.getColumnIndex(BorkostoloSzemely.COLUMN_TIMESTAMP)));

                borkostoloSzemely.add(borkostoloSzemelyek);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // Close db connection
        db.close();

        // Return borkostoloSzemely list
        return borkostoloSzemely;
    }

    public int updateKostolo(BorkostoloSzemely borkostoloSzemely) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BorkostoloSzemely.COLUMN_SZAKMAISAG, borkostoloSzemely.getSzakmaisagiErtek());
        values.put(BorkostoloSzemely.COLUMN_SZULDATUM, borkostoloSzemely.getSzuletesiDatum());
        values.put(BorkostoloSzemely.COLUMN_KNEV, borkostoloSzemely.getKeresztNev());
        values.put(BorkostoloSzemely.COLUMN_VNEV, borkostoloSzemely.getVezetekNev());
        values.put(BorkostoloSzemely.COLUMN_ID, borkostoloSzemely.getId());

        // Updating row
        return db.update(BorkostoloSzemely.TABLE_NAME, values, BorkostoloSzemely.COLUMN_ID + " =?",
                new String[]{String.valueOf(borkostoloSzemely.getId())});
    }

    public int getKostoloCount() {
        String countQuery = "SELECT * FROM " + BorkostoloSzemely.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // Return count
        return count;
    }

    public List<Borbiralat> getAllBorBiralat() {
        List<Borbiralat> borbiralatList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + Borbiralat.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Borbiralat borbiralatok = new Borbiralat();
                borbiralatok.setId((cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_ID))));
                borbiralatok.setBiraltBorID(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_BiraltBorID)));
                borbiralatok.setBiraloSzemelyID(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_BiraloSzemelyID)));
                borbiralatok.setMegjelenesTisztasag(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_MegjelenesTisztasag)));
                borbiralatok.setMegjelentesSzin(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_MegjelentesSzin)));
                borbiralatok.setIllatIntenzitas(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_IllatIntenzitas)));
                borbiralatok.setIllatKarakter(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_IllatKarakter)));
                borbiralatok.setIllatMinoseg(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_IllatMinoseg)));
                borbiralatok.setZamatIntenzitas(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_ZamatIntenzitas)));
                borbiralatok.setZamatKarakter(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_ZamatKarakter)));
                borbiralatok.setZamatMinoseg(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_ZamatMinoseg)));
                borbiralatok.setZamatHosszusag(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_ZamatHosszusag)));
                borbiralatok.setOsszbenyomas(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_Osszbenyomas)));
                borbiralatok.setOsszesen(cursor.getInt(cursor.getColumnIndex(Borbiralat.COLUMN_Osszesen)));

                borbiralatList.add(borbiralatok);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // Close db connection
        db.close();

        // Return borkostoloSzemely list
        return borbiralatList;
    }

    public long insertBorBiralat(int biraltBorId, int biraloSzemelyID, int osszesen) {

        // Get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        long id = insertBorBiralat(biraltBorId, biraloSzemelyID, osszesen, db);

        // Close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    private long insertBorBiralat(int biraltBorId, int biraloSzemelyID, int osszesen, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // no need to add them

        values.put(Borbiralat.COLUMN_BiraltBorID, biraltBorId);
        values.put(Borbiralat.COLUMN_BiraloSzemelyID, biraloSzemelyID);
        values.put(Borbiralat.COLUMN_Osszesen, osszesen);

        // Insert row
        return db.insert(Borbiralat.TABLE_NAME, null, values);
    }

    public long insertBorBiralat(int biraltBorId, int biraloSzemelyID, int megjelenesTisztasag,
                                 int megjelenesSzin, int illatIntenzitas, int illatKarakter,
                                 int illatMinoseg, int zamatIntezitas, int zamatKarakter,
                                 int zamatMinoseg, int zamatHosszusag, int osszebenyomas) {

        // Get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // no need to add them

        values.put(Borbiralat.COLUMN_BiraltBorID, biraltBorId);
        values.put(Borbiralat.COLUMN_BiraloSzemelyID, biraloSzemelyID);
        values.put(Borbiralat.COLUMN_MegjelenesTisztasag, megjelenesTisztasag);
        values.put(Borbiralat.COLUMN_MegjelentesSzin, megjelenesSzin);
        values.put(Borbiralat.COLUMN_IllatIntenzitas, illatIntenzitas);
        values.put(Borbiralat.COLUMN_IllatKarakter, illatKarakter);
        values.put(Borbiralat.COLUMN_IllatMinoseg, illatMinoseg);
        values.put(Borbiralat.COLUMN_ZamatIntenzitas, zamatIntezitas);
        values.put(Borbiralat.COLUMN_ZamatKarakter, zamatKarakter);
        values.put(Borbiralat.COLUMN_ZamatMinoseg, zamatMinoseg);
        values.put(Borbiralat.COLUMN_ZamatHosszusag, zamatHosszusag);
        values.put(Borbiralat.COLUMN_Osszbenyomas, osszebenyomas);
        int AllInAll = megjelenesTisztasag + megjelenesSzin + illatIntenzitas +
                illatKarakter + illatMinoseg + zamatIntezitas + zamatKarakter +
                zamatMinoseg + zamatHosszusag + osszebenyomas;
        values.put(Borbiralat.COLUMN_Osszesen, AllInAll);

        // Insert row
        long id = db.insert(Borbiralat.TABLE_NAME, null, values);

        // Close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public int updateBorBiralat(Borbiralat borbiralat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Borbiralat.COLUMN_BiraltBorID, borbiralat.getBiraltBorID());
        values.put(Borbiralat.COLUMN_BiraloSzemelyID, borbiralat.getBiraloSzemelyID());
        values.put(Borbiralat.COLUMN_MegjelenesTisztasag, borbiralat.getMegjelenesTisztasag());
        values.put(Borbiralat.COLUMN_MegjelentesSzin, borbiralat.getMegjelentesSzin());
        values.put(Borbiralat.COLUMN_IllatIntenzitas, borbiralat.getIllatIntenzitas());
        values.put(Borbiralat.COLUMN_IllatKarakter, borbiralat.getIllatKarakter());
        values.put(Borbiralat.COLUMN_IllatMinoseg, borbiralat.getIllatMinoseg());
        values.put(Borbiralat.COLUMN_ZamatIntenzitas, borbiralat.getZamatIntenzitas());
        values.put(Borbiralat.COLUMN_ZamatKarakter, borbiralat.getZamatKarakter());
        values.put(Borbiralat.COLUMN_ZamatMinoseg, borbiralat.getZamatMinoseg());
        values.put(Borbiralat.COLUMN_ZamatHosszusag, borbiralat.getZamatHosszusag());
        values.put(Borbiralat.COLUMN_Osszbenyomas, borbiralat.getOsszbenyomas());

        // Updating row
        return db.update(Borbiralat.TABLE_NAME, values, Borbiralat.COLUMN_ID + " =?",
                new String[]{String.valueOf(borbiralat.getId())});
    }

    public void deleteBorBiralat(Borbiralat borbiralat) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Borbiralat.TABLE_NAME, Borbiralat.COLUMN_ID + " =?",
                new String[]{String.valueOf(borbiralat.getId())});
        db.close();
    }

    public int updateBor(Bor borok) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Bor.COLUMN_GYARTO, borok.getBorGyarto());
        values.put(Bor.COLUMN_NEVE, borok.getBorNeve());
        values.put(Bor.COLUMN_FAJTA, borok.getBorFajta());
        values.put(Bor.COLUMN_SZIN, borok.getBorSzin());
        values.put(Bor.COLUMN_EVJARAT, borok.getBorEvjarat());
        values.put(Bor.COLUMN_ALKOHOLTARTAM, borok.getBorAlkohol_tartam());
        values.put(Bor.COLUMN_FOGYASZTASIHOMERSEKLET, borok.getBorFogyasztasiHomerseklet());

        // Updating row
        return db.update(Bor.TABLE_NAME, values, Bor.COLUMN_ID + " =?",
                new String[]{String.valueOf(borok.getId())});
    }

    public void deleteKostolo(BorkostoloSzemely borkostoloSzemely) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BorkostoloSzemely.TABLE_NAME, BorkostoloSzemely.COLUMN_ID + " =?",
                new String[]{String.valueOf(borkostoloSzemely.getId())});
        db.close();
    }

    public void deleteBor(Bor bor) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Bor.TABLE_NAME, Bor.COLUMN_ID + " =?",
                new String[]{String.valueOf(bor.getId())});
        db.close();
    }

    public long insertBor(String borGyarto, String borNeve, String borFajta, String borSzine, int borEvjarat, float borAlkholoTartam, int borFogyasztasiHom) {
        // Get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        long id = insertBor(borGyarto, borNeve, borFajta, borSzine, borEvjarat, borAlkholoTartam, borFogyasztasiHom, db);

        // Close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    private long insertBor(String borGyarto, String borNeve, String borFajta, String borSzine, int borEvjarat, float borAlkholoTartam, int borFogyasztasiHom, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // 'id' and 'timestamp' will be inserted automatically
        // no need to add them

        values.put(Bor.COLUMN_GYARTO, borGyarto);
        values.put(Bor.COLUMN_NEVE, borNeve);
        values.put(Bor.COLUMN_FAJTA, borFajta);
        values.put(Bor.COLUMN_SZIN, borSzine);
        values.put(Bor.COLUMN_EVJARAT, borEvjarat);
        values.put(Bor.COLUMN_ALKOHOLTARTAM, borAlkholoTartam);
        values.put(Bor.COLUMN_FOGYASZTASIHOMERSEKLET, borFogyasztasiHom);

        // Insert row
        return db.insert(Bor.TABLE_NAME, null, values);
    }

    public List<Bor> getAllBor() {
        List<Bor> borok = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + Bor.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bor bor = new Bor();
                bor.setId((cursor.getInt(cursor.getColumnIndex(Bor.COLUMN_ID))));
                bor.setBorGyarto(cursor.getString(cursor.getColumnIndex(Bor.COLUMN_GYARTO)));
                bor.setBorNeve(cursor.getString(cursor.getColumnIndex(Bor.COLUMN_NEVE)));
                bor.setBorFajta(cursor.getString(cursor.getColumnIndex(Bor.COLUMN_FAJTA)));
                bor.setBorSzin(cursor.getString(cursor.getColumnIndex(Bor.COLUMN_SZIN)));
                bor.setBorEvjarat(cursor.getInt(cursor.getColumnIndex(Bor.COLUMN_EVJARAT)));
                bor.setBorAlkohol_tartam(cursor.getFloat(cursor.getColumnIndex(Bor.COLUMN_ALKOHOLTARTAM)));
                bor.setBorFogyasztasiHomerseklet(cursor.getInt(cursor.getColumnIndex(Bor.COLUMN_FOGYASZTASIHOMERSEKLET)));
//                bor.setAtlagPontSzam(cursor.getFloat(cursor.getColumnIndex(Bor.COLUMN_ATLAGPONTSZAM)));

                borok.add(bor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // Close db connection
        db.close();

        // Return bor list
        return borok;
    }
}
