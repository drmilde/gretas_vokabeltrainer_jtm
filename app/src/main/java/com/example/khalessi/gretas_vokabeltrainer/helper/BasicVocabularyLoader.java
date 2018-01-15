package com.example.khalessi.gretas_vokabeltrainer.helper;

import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

/**
 * Created by milde on 14.01.18.
 */

public class BasicVocabularyLoader {
    private DatabaseHelper dbh = null;

    public BasicVocabularyLoader() {

        dbh = AppState.getInstance().getDatabaseHelper();

        dbh.recreateDatabase();
        dbh.insertSomeVocs();
        dbh.insertSomeUnits();

    }
}
