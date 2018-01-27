package com.example.khalessi.gretas_vokabeltrainer.database.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by milde on 24.01.18.
 */

@Dao
public interface VocabularyItemDao {

    @Insert
    long insertVocItem(VocabularyItem vocItem);

    @Update
    int updateVocItem(VocabularyItem vocItem);

    @Delete
    int deleteVocItem(VocabularyItem vocItem);

    // queries

    @Query("DELETE FROM voc_table WHERE _id=:id")
    int deleteVocItem(int id);

    @Query("DELETE FROM voc_table WHERE unitId=:unitId")
    int deleteVocItems(String unitId);

    @Query("DELETE FROM voc_table")
    int deleteAllVocItems();

    @Query("SELECT * FROM voc_table WHERE foreignLang=:foreignLang")
    List<VocabularyItem> getVocItemsForeingLang(String foreignLang);

    @Query("SELECT * FROM voc_table WHERE unitId=:unitId")
    List<VocabularyItem> getVocList(String unitId);

    @Query("SELECT * from voc_table")
    List<VocabularyItem> getVocabularyItemsData();

}
