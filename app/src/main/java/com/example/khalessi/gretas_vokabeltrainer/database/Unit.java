package com.example.khalessi.gretas_vokabeltrainer.database;

import java.util.ArrayList;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */


public class Unit {
    private String unitId;
    private String user;
    private String title;
    private String description;
    private int _id;

    // vocabulary item of unit
    private ArrayList<VocabularyItem> voclist = new ArrayList<VocabularyItem>();


    public Unit(String unitId, String user, String title, String description) {
        this(unitId, user, title, description, -1);
    }

    public Unit(String unitId, String user, String title, String description, int _id) {
        this.user = user;
        this.unitId = unitId;
        this.title = title;
        this.description = description;
        this._id = _id;
    }


    // getter

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

    public ArrayList<VocabularyItem> getVoclist() {
        return voclist;
    }

    // setter

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setVoclist(ArrayList<VocabularyItem> voclist) {
        this.voclist = voclist;
    }
}