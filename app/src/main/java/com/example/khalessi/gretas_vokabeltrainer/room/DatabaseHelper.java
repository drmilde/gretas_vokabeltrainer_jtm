package com.example.khalessi.gretas_vokabeltrainer.room;

import android.content.Context;

import java.util.List;

/**
 * Created by milde on 24.01.18.
 */

public class DatabaseHelper implements IDatabaseHelper {

    private Context context = null;

    GretasDatabase gdb = null;

    public DatabaseHelper(Context context) {
        this.context = context;

        // initialisiere Datenbank
        gdb = GretasDatabase.getDatabase(context);
    }


    @Override
    public void recreateDatabase() {
        GretasDatabase.recreateDB(context);
        gdb = GretasDatabase.getDatabase(context);
    }

    // *************************************
    // CRUD für Units ANFANG
    // *************************************

    @Override
    public boolean insertUnit(Unit unit) {
        long id = gdb.unitDao().insertUnit(unit);
        return (id > -1);
    }

    @Override
    public List<Unit> getUnitsData(boolean getVocList) {
        List<Unit> unitsData = gdb.unitDao().getUnitsData();

        for (Unit unit : unitsData) {
            if (getVocList) {
                List<VocabularyItem> vocList = gdb.vocabularyItemDao().getVocabularyData(unit.getUnitId());
                unit.setVoclist(vocList);
            } else {
                unit.setVoclist(null);
            }
        }
        return unitsData;
    }

    @Override
    public boolean updateUnit(int id, Unit unit) {
        unit.set_id(id);
        int affected = gdb.unitDao().updateUnit(unit);
        return (affected > 0);
    }

    @Override
    public boolean updateUnit(Unit unit) {
        int affected = gdb.unitDao().updateUnit(unit);
        return (affected > 0);
    }

    @Override
    public int deleteUnit(Integer id) {
        int affected = gdb.unitDao().deleteUnit(id);
        return affected;
    }

    @Override
    public int deleteUnit(Unit unit) {
        int affected = gdb.unitDao().deleteUnit(unit);
        return affected;
    }

    @Override
    public Unit getUnit(String unitId, boolean getVocList) {
        Unit unit = gdb.unitDao().getUnit(unitId);

        if (getVocList) {
            List<VocabularyItem> vocList = gdb.vocabularyItemDao().getVocabularyData(unit.getUnitId());
            unit.setVoclist(vocList);
        } else {
            unit.setVoclist(null);
        }

        return unit;
    }

    @Override
    public int getColumnId(String unitId) {
        Unit unit = gdb.unitDao().getUnit(unitId);
        if (unit != null) {
            return unit.get_id();
        }
        return -1;
    }

    @Override
    public void deleteSomeUnits() {
        // nur zum testen, wird nicht implementiert hier
        // kann weg
    }


    // CRUD für VocabularyItem ANFANG

    @Override
    public boolean insertVocabulary(String unitId, String foreignLang, String nativeLang, String description) {
        return false;
    }

    @Override
    public boolean insertVocabulary(VocabularyItem vocItem) {
        return false;
    }

    @Override
    public boolean updateVocabulary(int id, VocabularyItem vocItem) {
        return false;
    }

    @Override
    public boolean updateVocabulary(VocabularyItem vocItem) {
        return false;
    }

    @Override
    public List<VocabularyItem> getVocabularyData() {
        return null;
    }

    @Override
    public List<VocabularyItem> getVocabularyDataByUnitId(String unitId) {
        return null;
    }

    @Override
    public int getVocabularyIdForForeign(String foreign) {
        return 0;
    }

    @Override
    public VocabularyItem getVocabularyItemForForeign(String foreign) {
        return null;
    }
}
