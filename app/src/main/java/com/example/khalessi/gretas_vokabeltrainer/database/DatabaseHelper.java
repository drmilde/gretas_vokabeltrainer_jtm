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

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB_Units.db";


    // table units
    public static final String UNITS_TABLE_NAME = "UnitTable";
    public static final String UNITS_COLUMN_ID = "_id";
    public static final String UNITS_COLUMN_UNIT_ID = "c_unitId";
    public static final String UNITS_COLUMN_USER = "c_user";
    public static final String UNITS_COLUMN_TITLE = "c_title";
    public static final String UNITS_COLUMN_DESCRIPTION = "c_description";

    // table vocabulary
    public static final String VOCABULARY_TABLE_NAME = "VocTable";
    public static final String VOCABULARY_COLUMN_ID = "_id";
    public static final String VOCABULARY_COLUMN_UNIT_ID = "c_unitId";
    public static final String VOCABULARY_COLUMN_FOREIGN_LANG = "c_foreign";
    public static final String VOCABULARY_COLUMN_NATIVE_LANG = "c_native";
    public static final String VOCABULARY_COLUMN_DESCRIPTION = "c_description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        setupUnitsTable(db);
        setupVocTable(db);
    }

    private void setupVocTable(SQLiteDatabase db) {
        //TODO drop table entfernen... später
        db.execSQL("DROP TABLE IF EXISTS " + VOCABULARY_TABLE_NAME);
        db.execSQL("create table  " + VOCABULARY_TABLE_NAME +
                "(" + VOCABULARY_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL,"
                + VOCABULARY_COLUMN_UNIT_ID + " Text,"
                + VOCABULARY_COLUMN_FOREIGN_LANG + " Text,"
                + VOCABULARY_COLUMN_NATIVE_LANG + " Text,"
                + VOCABULARY_COLUMN_DESCRIPTION + " Text)"
        );
    }

    private void setupUnitsTable(SQLiteDatabase db) {
        //TODO drop table entfernen... später
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        db.execSQL("create table  " + UNITS_TABLE_NAME +
                "(" + UNITS_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL,"
                + UNITS_COLUMN_USER + " Text,"
                + UNITS_COLUMN_UNIT_ID + " Text,"
                + UNITS_COLUMN_DESCRIPTION + " Text,"
                + UNITS_COLUMN_TITLE + " Text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOCABULARY_TABLE_NAME);
        onCreate(db);
    }

    public void recreateDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOCABULARY_TABLE_NAME);
        onCreate(db);
    }


    //*****************************************************
    // CRUD Operationen Unit ANFANG
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
                            result.getString(result.getColumnIndex(UNITS_COLUMN_DESCRIPTION)),
                            Integer.parseInt(result.getString(result.getColumnIndex("_id")))
                    )
                    //)
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

        db.update(UNITS_TABLE_NAME, contentValues, UNITS_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteUnit(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UNITS_TABLE_NAME,
                UNITS_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }


    public Unit getUnit(String unitId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                UNITS_COLUMN_UNIT_ID,
                UNITS_COLUMN_USER,
                UNITS_COLUMN_TITLE,
                UNITS_COLUMN_DESCRIPTION,
                UNITS_COLUMN_ID
        };

        String pattern = UNITS_COLUMN_UNIT_ID + "=?";

        String[] values = {
                unitId
        };

        Cursor cursor = db.query(UNITS_TABLE_NAME,
                columns, pattern, values,
                null, null, null, null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) { // ist da überhaupt ein Datensatz
                Unit unit = new Unit(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Integer.parseInt(cursor.getString(4))
                );

                return unit;
            }
        }

        // TODO muss der cursor hier geschlossen werden ????

        return null;
    }

    public void insertSomeUnits() {
        insertUnit("livingRoom", "Greta", "In the living room", "On top of the world.");

        insertUnit(UnitIdGenerator.generate(), "Greta", "In the living room", "Watching TV is fun!");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Going to school", "This is less :)");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Having a party", "Not yet, my dear.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Melting chocolate", "Smells good.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Making a torch", "You light up my life.");
        insertUnit(UnitIdGenerator.generate(), "Greta", "Kitchen cleaning", "Help me, supermom.");

    }

    public void deleteSome() {
        deleteUnit(4);
        deleteUnit(5);
    }

    //*****************************************************
    // CRUD Operationen für Unit ENDE
    //*****************************************************


    //*****************************************************
    // CRUD Operationen für Vokabeln ANFANG
    //*****************************************************


    public boolean insertVocabulary(String unitId, String foreignLang, String nativeLang, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(VOCABULARY_COLUMN_UNIT_ID, unitId);
        contentValues.put(VOCABULARY_COLUMN_FOREIGN_LANG, foreignLang);
        contentValues.put(VOCABULARY_COLUMN_NATIVE_LANG, nativeLang);
        contentValues.put(VOCABULARY_COLUMN_DESCRIPTION, description);

        db.insert(VOCABULARY_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<VocabularyItem> getVocabularyData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<VocabularyItem> voclist = new ArrayList<VocabularyItem>();

        Cursor result = db.rawQuery("select * from " + VOCABULARY_TABLE_NAME, null);

        while (result.moveToNext()) {
            voclist.add(new VocabularyItem(
                            Integer.parseInt(result.getString(result.getColumnIndex(VOCABULARY_COLUMN_ID))),
                            result.getString(result.getColumnIndex(VOCABULARY_COLUMN_FOREIGN_LANG)),
                            result.getString(result.getColumnIndex(VOCABULARY_COLUMN_NATIVE_LANG)),
                            result.getString(result.getColumnIndex(VOCABULARY_COLUMN_DESCRIPTION)),
                            result.getString(result.getColumnIndex(VOCABULARY_COLUMN_UNIT_ID))
                    )
            );
        }

        return voclist;
    }

    public ArrayList<VocabularyItem> getVocabularyData(String unitId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<VocabularyItem> voclist = new ArrayList<VocabularyItem>();

        String[] columns = {
                VOCABULARY_COLUMN_ID,
                VOCABULARY_COLUMN_FOREIGN_LANG,
                VOCABULARY_COLUMN_NATIVE_LANG,
                VOCABULARY_COLUMN_DESCRIPTION,
                VOCABULARY_COLUMN_UNIT_ID
        };

        String pattern = UNITS_COLUMN_UNIT_ID + "=?";

        String[] values = {
                unitId
        };

        Cursor result = db.query(VOCABULARY_TABLE_NAME,
                columns, pattern, values,
                null, null, null, null
        );

        while (result.moveToNext()) {
            voclist.add(new VocabularyItem(
                    Integer.parseInt(result.getString(0)),
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4))
            );
        }

        return voclist;
    }

    public void insertSomeVocs() {
        insertVocabulary(UnitIdGenerator.generate(), "to learn", "lernen", "verb, inifinitiv");
        insertVocabulary(UnitIdGenerator.generate(), "to run", "laufen", "verb, inifinitiv");
        insertVocabulary(UnitIdGenerator.generate(), "to hide", "verstecken", "verb, inifinitiv");
        insertVocabulary(UnitIdGenerator.generate(), "to seek", "suchen", "verb, inifinitiv");
        insertVocabulary(UnitIdGenerator.generate(), "to watch", "schauen", "verb, inifinitiv");
        insertVocabulary(UnitIdGenerator.generate(), "to ring", "klingeln", "verb, inifinitiv");

        insertVocabulary("livingRoom", "to rise", "aufsteigen", "verb, inifinitiv");
        insertVocabulary("livingRoom", "to fall", "fallen", "verb, inifinitiv");
        insertVocabulary("livingRoom", "to sing", "singen", "verb, inifinitiv");
        insertVocabulary("livingRoom", "to think", "denken", "verb, inifinitiv");
    }


    //*****************************************************
    // CRUD Operationen für Vokabeln ENDE
    //*****************************************************
}
