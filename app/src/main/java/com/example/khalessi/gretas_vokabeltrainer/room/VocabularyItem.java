package com.example.khalessi.gretas_vokabeltrainer.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by milde on 08.01.18.
 */

@Entity(tableName = "voc_table")
public class VocabularyItem {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String foreignLang;
    private String nativeLang;
    private String description;
    @NonNull
    private String unitId;
    private int level1;
    private int level2;


    // Konstruktoren
    public VocabularyItem(String unitId, String foreignLang, String nativeLang, String description) {
        this.foreignLang = foreignLang;
        this.nativeLang = nativeLang;
        this.description = description;
        this.unitId = unitId;
        this.level1 = 0;
        this.level2 = 0;
    }

    public VocabularyItem(int _id, String foreignLang, String nativeLang, String description, String unitId,
                          int level1, int level2) {
        this._id = _id;
        this.foreignLang = foreignLang;
        this.nativeLang = nativeLang;
        this.description = description;
        this.unitId = unitId;
        this.level1 = level1;
        this.level2 = level2;
    }

    // getter
    public int get_id() {
        return _id;
    }

    public String getForeignLang() {
        return foreignLang;
    }

    public String getNativeLang() {
        return nativeLang;
    }

    public String getDescription() {
        return description;
    }

    public String getUnitId() {
        return unitId;
    }

    public int getLevel1() {
        return level1;
    }

    public int getLevel2() {
        return level2;
    }


    // setter

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setForeignLang(String foreignLang) {
        this.foreignLang = foreignLang;
    }

    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

}
