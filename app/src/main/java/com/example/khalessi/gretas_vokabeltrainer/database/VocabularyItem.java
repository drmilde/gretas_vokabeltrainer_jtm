package com.example.khalessi.gretas_vokabeltrainer.database;

/**
 * Created by milde on 08.01.18.
 */

public class VocabularyItem {
    private int _id;
    private String foreignLang;
    private String nativeLang;
    private String description;
    private String unitId;


    public VocabularyItem(String unitId, String foreignLang, String nativeLang, String description) {
        this.foreignLang = foreignLang;
        this.nativeLang = nativeLang;
        this.description = description;
        this.unitId = unitId;
    }

    public VocabularyItem(int _id, String foreignLang, String nativeLang, String description, String unitId) {
        this._id = _id;
        this.foreignLang = foreignLang;
        this.nativeLang = nativeLang;
        this.description = description;
        this.unitId = unitId;
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
}
