package com.example.khalessi.gretas_vokabeltrainer.database;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


public class Unit {
    private String user;
    private String unitId;
    private String title;
    private String description;

    public Unit(String unitId, String user, String title, String description) {
        this.user = user;
        this.unitId = unitId;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}