package com.example.khalessi.gretas_vokabeltrainer.database_vocabulary;

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
    public static final String UNITS_COLUMN_USER = "c_user";
    public static final String UNITS_COLUMN_UNIT_ID = "c_unitId";
    public static final String UNITS_COLUMN_DESCRIPTION = "c_description";

    public UnitDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO drop table entfernen... später
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        db.execSQL("create table  " + UNITS_TABLE_NAME +
                "(_id integer primary key AUTOINCREMENT NOT NULL,"
                + UNITS_COLUMN_USER + " Text,"
                + UNITS_COLUMN_UNIT_ID + " Text,"
                + UNITS_COLUMN_DESCRIPTION + " Text)"
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

    public boolean insertUnit(String unitId, String user, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("c_user", user);
        contentValues.put("c_unitId", unitId);
        contentValues.put("c_description", description);

        db.insert(UNITS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Units> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Units> car = new ArrayList<Units>();
        Cursor result = db.rawQuery("select * from " + UNITS_TABLE_NAME, null);
        while (result.moveToNext()) {
            car.add(new Units(result.getString(result.getColumnIndex(UNITS_COLUMN_UNIT_ID)),
                    result.getString(result.getColumnIndex(UNITS_COLUMN_USER)),
                    result.getString(result.getColumnIndex(UNITS_COLUMN_DESCRIPTION))));

        }
        return car;
    }

    public boolean updateUnit(int id, String user, String unitId, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("c_user", user);
        contentValues.put("c_unitId", unitId);
        contentValues.put("c_description", description);

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

        insertUnit(UnitIdGenerator.generate(), "greta", "Honda Civic");
        insertUnit(UnitIdGenerator.generate(), "greta", "Rolls Royce");
        insertUnit(UnitIdGenerator.generate(), "greta", "Honda Civic");
        insertUnit(UnitIdGenerator.generate(), "greta", "Nissan Patrol <3");
        insertUnit(UnitIdGenerator.generate(), "greta", "Tiida");
        insertUnit(UnitIdGenerator.generate(), "greta", "Rolls Royce");

    }

}
