package com.example.khalessi.gretas_vokabeltrainer.database;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


public class Unit {
    String user;
    String unitId;
    String description;

    public Unit(String unitId, String user, String description) {
        this.unitId = unitId;
        this.user = user;
        this.description = description;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}