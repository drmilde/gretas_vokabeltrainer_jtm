package com.example.khalessi.gretas_vokabeltrainer.room;

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
    void insertVocItem(VocabularyItem vocItem);

    @Update
    void updateVocItem(VocabularyItem vocItem);

    @Delete
    void deleteVocItem(VocabularyItem vocItem);

    @Query("DELETE FROM voc_table")
    void deleteAllVocItems();

    @Query("SELECT * FROM voc_table WHERE foreignLang=:foreignLang")
    VocabularyItem getVocabularyItem(String foreignLang);

    @Query("SELECT * from voc_table")
    List<VocabularyItem> getVocabularyData();

}
