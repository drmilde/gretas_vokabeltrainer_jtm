package com.example.khalessi.gretas_vokabeltrainer.state;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;

/**
 * Created by milde on 05.01.18.
 */

public class ApplicationState {
    private static final ApplicationState ourInstance = new ApplicationState();

    private Unit currentUnit = null;
    private String currentUserName = "Miau";

    public static ApplicationState getInstance() {
        return ourInstance;
    }

    private ApplicationState() {
        // TODO muss hier was rein ?, also gibt es eine sinnvolle Initialisierung ?
    }


    // getter

    public Unit getCurrentUnit() {
        return currentUnit;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }
}
