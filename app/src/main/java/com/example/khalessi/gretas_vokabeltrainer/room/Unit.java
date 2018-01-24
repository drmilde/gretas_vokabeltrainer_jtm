package com.example.khalessi.gretas_vokabeltrainer.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;

import java.util.ArrayList;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */

@Entity(tableName = "unit_table")
public class Unit {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @NonNull
    private String unitId;
    private String user;
    private String title;
    private String description;

    // vocabulary item of unit
    @Ignore
    private ArrayList<VocabularyItem> voclist = new ArrayList<VocabularyItem>();


    // Konstruktoren
    @Ignore
    public Unit(String unitId, String user, String title, String description) {
        // id wird nicht gesetzt ?
        this.unitId = unitId;
        this.user = user;
        this.title = title;
        this.description = description;
    }

    public Unit(int _id, @NonNull String unitId, String user, String title, String description) {
        this._id = _id;
        this.unitId = unitId;
        this.user = user;
        this.title = title;
        this.description = description;
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