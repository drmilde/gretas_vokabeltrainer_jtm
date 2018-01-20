package com.example.khalessi.gretas_vokabeltrainer.helper;

import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.VocabularyItem;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

/**
 * Created by milde on 14.01.18.
 */

public class BasicVocabularyLoader {

    private DatabaseHelper dbh = null;

    public BasicVocabularyLoader() {
        dbh = AppState.getInstance().getDatabaseHelper();
    }


    public void resetDatabase() {
        dbh.recreateDatabase();

        insertSomeVocs();
        insertSomeUnits();
    }

    // Interface routinen

    public ArrayList<VocabularyItem> getVocabularyData(String unitId) {
        return dbh.getVocabularyDataByUnitId(unitId);
    }

    public ArrayList<VocabularyItem> getVocabularyData() {
        return dbh.getVocabularyData();
    }

    public ArrayList<Unit> getUnitsData(boolean getVocList) {
        return dbh.getUnitsData(getVocList);
    }

    public Unit getUnit(String unitId, boolean getVocList) {
        return dbh.getUnit(unitId, getVocList);
    }

    public void deleteUnit(int id) {
        dbh.deleteUnit(id);
    }

    public void deleteSomeUnits() {
        dbh.deleteSomeUnits();
    }

    public int getVocabularyId(String foreign) {
        return dbh.getVocabularyId(foreign);
    }

    public ArrayList<VocabularyItem> getVocabularyDataByUnitId(String unitId) {
        return dbh.getVocabularyDataByUnitId(unitId);
    }


    // setup content

    private void insertSomeVocs() {
        dbh.insertVocabulary("livingRoom", "to learn", "lernen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to run", "laufen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to hide", "verstecken", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to seek", "suchen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to watch", "schauen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to ring", "klingeln", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to rise", "aufsteigen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to fall", "fallen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to sing", "singen", "verb, inifinitiv");
        dbh.insertVocabulary("livingRoom", "to think", "denken", "verb, inifinitiv");

        dbh.insertVocabulary("livingRoom", "a pillow", "ein Kissen", "beschreibung");
        dbh.insertVocabulary("livingRoom", "a potted plant", "eine Topfpflanze", "beschreibung");
        dbh.insertVocabulary("livingRoom", "a set of speakers", "ein Lautsprecherpaar", "beschreibung");
        dbh.insertVocabulary("livingRoom", "a stereo system", "eine Stereoanlage", "beschreibung");
        dbh.insertVocabulary("livingRoom", "a vase of flowers", "ein Vase mit Blumen", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the armchair", "der Sessel", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the bookcase", "das Bücherregal", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the carpet", "der Teppich", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the chair", "der Stuhl", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the chimney", "der Schornstein", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the cupboard", "die Vitrine", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the curtains", "die Gardinen", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the drapes", "die Gardinen, die Vorhänge", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the fireplace", "der Kamin", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the shades", "die Jalousien", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the sofa", "das Sofa", "beschreibung");
        dbh.insertVocabulary("livingRoom", "the television set", "der Fernseher", "beschreibung");
    }

    private void insertSomeUnits() {
        dbh.insertUnit(new Unit("livingRoom", "Greta", "In the living room", "On top of the world."));
        dbh.insertUnit(new Unit("GoingToSchool", "Greta", "Going to school", "This is less :)"));
        dbh.insertUnit(new Unit("HavingAParty", "Greta", "Having a party", "Not yet, my dear."));
        dbh.insertUnit(new Unit("MeltingChocolate", "Greta", "Melting chocolate", "Smells good."));
        dbh.insertUnit(new Unit("MakingATorch", "Greta", "Making a torch", "You light up my life."));
        dbh.insertUnit(new Unit("KitchenCleaning", "Greta", "Kitchen cleaning", "Help me."));
    }

}
