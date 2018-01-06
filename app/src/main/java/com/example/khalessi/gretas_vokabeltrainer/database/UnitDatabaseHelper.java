package com.example.khalessi.gretas_vokabeltrainer.database;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UnitDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB_Units.db";
    public static final String UNITS_TABLE_NAME = "UnitNumber";
    // public static final String CARS_COLUMN_ID = "id";
    public static final String UNITS_COLUMN_UNIT_ID = "c_unitId";
    public static final String UNITS_COLUMN_USER = "c_user";
    public static final String UNITS_COLUMN_TITLE = "c_title";
    public static final String UNITS_COLUMN_DESCRIPTION = "c_description";

    public UnitDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        setupUnitsDB(db);
        // TODO hier muss das Setup der Vokabel Tabelle hin
    }

    private void setupUnitsDB(SQLiteDatabase db) {
        //TODO drop table entfernen... später
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        db.execSQL("create table  " + UNITS_TABLE_NAME +
                "(_id integer primary key AUTOINCREMENT NOT NULL,"
                + UNITS_COLUMN_USER + " Text,"
                + UNITS_COLUMN_UNIT_ID + " Text,"
                + UNITS_COLUMN_DESCRIPTION + " Text,"
                + UNITS_COLUMN_TITLE + " Text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        onCreate(db);
    }

    public void recreateDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        onCreate(db);
    }


    //*****************************************************
    // CRUD Operationen Unit Anfang
    //*****************************************************

    public boolean insertUnit(String unitId, String user, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UNITS_COLUMN_USER, user);
        contentValues.put(UNITS_COLUMN_UNIT_ID, unitId);
        contentValues.put(UNITS_COLUMN_DESCRIPTION, description);
        contentValues.put(UNITS_COLUMN_TITLE, title);

        db.insert(UNITS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Unit> getUnitsData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Unit> units = new ArrayList<Unit>();
        Cursor result = db.rawQuery("select * from " + UNITS_TABLE_NAME, null);

        while (result.moveToNext()) {
            units.add(new Unit(
                    result.getString(result.getColumnIndex(UNITS_COLUMN_UNIT_ID)),
                    result.getString(result.getColumnIndex(UNITS_COLUMN_USER)),
                    result.getString(result.getColumnIndex(UNITS_COLUMN_TITLE)),
                    result.getString(result.getColumnIndex(UNITS_COLUMN_DESCRIPTION)))
            );

        }

        return units;
    }

    public boolean updateUnit(int id, String unitId, String user, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UNITS_COLUMN_USER, user);
        contentValues.put(UNITS_COLUMN_UNIT_ID, unitId);
        contentValues.put(UNITS_COLUMN_DESCRIPTION, description);
        contentValues.put(UNITS_COLUMN_TITLE, title);

        db.update(UNITS_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteUnit(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UNITS_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public void insertSomeUnits() {

        insertUnit(UnitIdGenerator.generate(), "Greta", "In the living room", "Watching TV is fun!");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Going to school", "This is less :)");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Having a party", "Not yet, my dear.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Melting chocolate", "Smells good.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Making a torch", "Light up my life.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Kitchen cleaning", "Help me, supermom.");

    }

    //*****************************************************
    // CRUD Operationen für Unit ENDE
    //*****************************************************





    //*****************************************************
    // CRUD Operationen für Vokabeln ANFANG
    //*****************************************************

    // TODO hier müssen die CRUD Operation für die Vokabel Tabelle hin

    //*****************************************************
    // CRUD Operationen für Vokabeln ENDE
    //*****************************************************
}
