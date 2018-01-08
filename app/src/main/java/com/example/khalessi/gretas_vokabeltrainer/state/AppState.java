package com.example.khalessi.gretas_vokabeltrainer.state;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;

/**
 * Created by milde on 05.01.18.
 */

public class AppState {
    private static final AppState ourInstance = new AppState();

    private Unit currentUnit = null;
    private String currentUserName = "Miau";
    private int currentDesign = 0; //

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

    public int getCurrentDesign() {
        return currentDesign;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    // setter


    public void setCurrentUnit(Unit currentUnit) {
        this.currentUnit = currentUnit;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public void setCurrentDesign(int currentDesign) {
        this.currentDesign = currentDesign;
    }

    public void setDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
}
