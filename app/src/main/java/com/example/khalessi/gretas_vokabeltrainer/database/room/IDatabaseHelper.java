package com.example.khalessi.gretas_vokabeltrainer.database.room;


import java.util.List;

/**
 * Created by milde on 24.01.18.
 */

interface IDatabaseHelper {
    void recreateDatabase();

    boolean insertUnit(Unit unit);

    List<Unit> getUnitsData(boolean getVocList);

    boolean updateUnit(int id, Unit unit);

    boolean updateUnit(Unit unit);

    int deleteUnit(Integer id);

    int deleteUnit(Unit unit);

    Unit getUnit(String unitId, boolean getVocList);

    int getColumnId(String unitId);

    void deleteSomeUnits();

    boolean insertVocabulary(String unitId, String foreignLang, String nativeLang, String description);

    boolean insertVocabulary(VocabularyItem vocItem);

    boolean updateVocabulary(int id, VocabularyItem vocItem);

    boolean updateVocabulary(VocabularyItem vocItem);

    List<VocabularyItem> getVocabularyData();

    List<VocabularyItem> getVocabularyDataByUnitId(String unitId);

    int getVocabularyIdForForeign(String foreignLang);

    VocabularyItem getVocabularyItemForForeign(String foreignLang);
}
