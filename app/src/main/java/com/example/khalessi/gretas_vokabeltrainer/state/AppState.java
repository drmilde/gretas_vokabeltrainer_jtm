package com.example.khalessi.gretas_vokabeltrainer.state;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;

/**
 * Created by milde on 05.01.18.
 */

public class AppState {
    private static final AppState ourInstance = new AppState();

    private Unit currentUnit = null;
    private VocabularyItem currentVoc = null;
    private String currentUserName = "Miau";

    private DatabaseHelper databaseHelper;

    public static AppState getInstance() {
        return ourInstance;
    }

    private AppState() {
        // TODO muss hier was rein ?, also gibt es eine sinnvolle Initialisierung ?
    }


    // getter

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public VocabularyItem getCurrentVoc() {
        return currentVoc;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    // setter


    public void setCurrentUnit(Unit currentUnit) {
        this.currentUnit = currentUnit;
    }

    public void setCurrentVoc(VocabularyItem currentVoc) {
        this.currentVoc = currentVoc;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public void setDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
}
