package com.example.khalessi.gretas_vokabeltrainer.helper;

import com.example.khalessi.gretas_vokabeltrainer.database.room.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database.room.Unit;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

/**
 * Created by milde on 14.01.18.
 */

public class BasicVocabularyLoader {

    private DatabaseHelper dbh = null;
    private BasicEnglishVocab bec = null;

    public BasicVocabularyLoader() {
        dbh = AppState.getInstance().getDatabaseHelper();
        bec = new BasicEnglishVocab();
    }


    public void resetDatabase() {
        dbh.recreateDatabase();

        setupBasicVocabAndUnits();
    }

    // setup content

    private void setupBasicVocabAndUnits() {
        createWohnzimmer();

        createUnit("GoingToSchool", "Greta", "Going to school", "in school", bec.getKlassenzimmer());
        createUnit("Kitchen", "Greta", "Kitchen", "cooking and baking", bec.getKueche());
        createUnit("Animals", "Greta", "Animals", "living creatures", bec.getTiere());
        createUnit("Oceans", "Greta", "Oceans", "the oceans of the world", bec.getOzeane());
        createUnit("Asking questions", "Greta", "Asking questions", "Asking questions", bec.getFragewoerter());
        createUnit("say hello", "Greta", "Greetings", "how to say hello", bec.getBegruessung());
        createUnit("inside the zoo", "Greta", "The zoo", "inside the zoo", bec.getZoo());
        createUnit("merry christmas", "Greta", "Christmas", "have a merry little christmas", bec.getWeihnachten());
        createUnit("at the beach", "Greta", "At the beach", "at the beach", bec.getStrand());
        createUnit("suitcase", "Greta", "Suitcase", "inside my suitcase", bec.getKoffer());
        createUnit("The body", "Greta", "Body parts", "body parts", bec.getKoerper());
        createUnit("getting dressed", "Greta", "Clothes", "getting dressed", bec.getKleidung());
        createUnit("bedroom", "Greta", "Bedroom", "the bedroom", bec.getSchlafzimmer());
        createUnit("bath room", "Greta", "Bathroom", "the bath room", bec.getBadezimmer());
        createUnit("the house", "Greta", "House", "inside a house", bec.getHaus());
        createUnit("food", "Greta", "Food", "something to eat", bec.getLebensmittel());
        createUnit("colors", "Greta", "colors", "color up your life", bec.getFarben());
        createUnit("months", "Greta", "Months", "months of the year", bec.getMonate());
        createUnit("drinks", "Greta", "Drinks", "something to drink", bec.getGetraenke());
        createUnit("numbers", "Greta", "Numbers", "counting is fun", bec.getZahlen());
        createUnit("tools", "Greta", "Tools", "tools and the work shop", bec.getWerkzeuge());
        createUnit("school subject", "Greta", "School subjects", "topics you learn at school", bec.getSchulfaecher());
        createUnit("school bag", "Greta", "School bag", "things, you need at school", bec.getSchultasche());
        createUnit("traffic", "Greta", "Traffic", "traffic", bec.getVerkehr());

        dbh.insertUnit(new Unit("HavingAParty", "Greta", "Having a party", "Not yet, my dear."));
        dbh.insertUnit(new Unit("MeltingChocolate", "Greta", "Melting chocolate", "Smells good."));
        dbh.insertUnit(new Unit("MakingATorch", "Greta", "Making a torch", "You light up my life."));
        dbh.insertUnit(new Unit("KitchenCleaning", "Greta", "Kitchen cleaning", "Help me."));

    }


    //
    private void createUnit(String unitId, String user, String title, String description, String[][] words) {
        dbh.insertUnit(new Unit(unitId, user, title, description));

        insertPairs(unitId, description, words);
    }

    private void insertPairs(String unitId, String description, String[][] words) {
        for (int i = 0; i < words.length; i++) {
            String[] pair = words[i];
            dbh.insertVocabulary(unitId, pair[1], pair[0], description);
        }
    }

    //
    private void createWohnzimmer() {
        dbh.insertUnit(new Unit("livingRoom", "Greta", "In the living room", "On top of the world."));

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

        insertPairs("livingRoom", "beschreibung", bec.getWohnzimmer());
    }

}
