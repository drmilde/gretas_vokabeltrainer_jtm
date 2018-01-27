package com.example.khalessi.gretas_vokabeltrainer.database.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.khalessi.gretas_vokabeltrainer.helper.BasicEnglishVocab;

/**
 * Created by milde on 24.01.18.
 */

@Database(entities = {Unit.class, VocabularyItem.class}, version = 1)
public abstract class GretasDatabase extends RoomDatabase {

    private static String DB_NAME ="gretas_database";

    public abstract UnitDao unitDao();

    public abstract VocabularyItemDao vocabularyItemDao();

    private static GretasDatabase INSTANCE = null;

    static GretasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            recreateDB(context);
        }
        return INSTANCE;
    }

    public static void recreateDB(Context context) {
        synchronized (GretasDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        GretasDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(sRoomDatabaseCallback)
                        .build();

            }
        }

        // TODO remove allowMainThreadQueries in Code PUUUH, hard one here
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UnitDao uDao;
        private final VocabularyItemDao vDao;

        private BasicEnglishVocab bec = new BasicEnglishVocab();

        PopulateDbAsync(GretasDatabase db) {
            uDao = db.unitDao();
            vDao = db.vocabularyItemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            setupUnits();
            setupVocabulary();

            return null;
        }

        private void setupVocabulary() {
            vDao.deleteAllVocItems();
            insertPairs("GoingToSchool","in school", bec.getKlassenzimmer());
            insertPairs("Kitchen","cooking and baking", bec.getKueche());
            insertPairs("Animals","living creatures", bec.getTiere());
            insertPairs("Animals","living creatures", bec.getTiere());
            insertPairs("Oceans","the oceans of the world", bec.getOzeane());
            insertPairs("Asking questions","Asking questions", bec.getFragewoerter());
            insertPairs("say hello","how to say hello", bec.getBegruessung());
            insertPairs("inside the zoo","inside the zoo", bec.getZoo());
            insertPairs("merry christmas","have a merry little christmas", bec.getWeihnachten());
            insertPairs("at the beach","at the beach", bec.getStrand());
            insertPairs("suitcase","inside my suitcase", bec.getKoffer());
            insertPairs("The body","body parts", bec.getKoerper());
            insertPairs("getting dressed","getting dressed", bec.getKleidung());
            insertPairs("bedroom","the bedroom", bec.getSchlafzimmer());
            insertPairs("bath room","the bath room", bec.getBadezimmer());
            insertPairs("the house","inside a house", bec.getHaus());
            insertPairs("food","something to eat", bec.getLebensmittel());
            insertPairs("colors","color up your life", bec.getFarben());
            insertPairs("months","months of the year", bec.getMonate());
            insertPairs("drinks","something to drink", bec.getGetraenke());
            insertPairs("numbers","counting is fun", bec.getZahlen());
            insertPairs("tools","tools and the work shop", bec.getWerkzeuge());
            insertPairs("school subject","topics you learn at school", bec.getSchulfaecher());
            insertPairs("school bag","things, you need at school", bec.getSchultasche());
            insertPairs("traffic","traffic", bec.getVerkehr());

        }

        private void setupUnits() {
            uDao.deleteAllUnits();
            uDao.insertUnit(new Unit("GoingToSchool","Greta","Going to school","in school"));
            uDao.insertUnit(new Unit("Kitchen","Greta","Kitchen","cooking and baking"));
            uDao.insertUnit(new Unit("Animals","Greta","Animals","living creatures"));
            uDao.insertUnit(new Unit("Oceans","Greta","Oceans","the oceans of the world"));
            uDao.insertUnit(new Unit("Asking questions","Greta","Asking questions","Asking questions"));
            uDao.insertUnit(new Unit("say hello","Greta","Greetings","how to say hello"));
            uDao.insertUnit(new Unit("inside the zoo","Greta","The zoo","inside the zoo"));
            uDao.insertUnit(new Unit("merry christmas","Greta","Christmas","have a merry little christmas"));
            uDao.insertUnit(new Unit("at the beach","Greta","At the beach","at the beach"));
            uDao.insertUnit(new Unit("suitcase","Greta","Suitcase","inside my suitcase"));
            uDao.insertUnit(new Unit("The body","Greta","Body parts","body parts"));
            uDao.insertUnit(new Unit("getting dressed","Greta","Clothes","getting dressed"));
            uDao.insertUnit(new Unit("bedroom","Greta","Bedroom","the bedroom"));
            uDao.insertUnit(new Unit("bath room","Greta","Bathroom","the bath room"));
            uDao.insertUnit(new Unit("the house","Greta","House","inside a house"));
            uDao.insertUnit(new Unit("food","Greta","Food","something to eat"));
            uDao.insertUnit(new Unit("colors","Greta","colors","color up your life"));
            uDao.insertUnit(new Unit("months","Greta","Months","months of the year"));
            uDao.insertUnit(new Unit("drinks","Greta","Drinks","something to drink"));
            uDao.insertUnit(new Unit("numbers","Greta","Numbers","counting is fun"));
            uDao.insertUnit(new Unit("tools","Greta","Tools","tools and the work shop"));
            uDao.insertUnit(new Unit("school subject","Greta","School subjects","topics you learn at school"));
            uDao.insertUnit(new Unit("school bag","Greta","School bag","things, you need at school"));
            uDao.insertUnit(new Unit("traffic","Greta","Traffic","traffic"));
        }


        private void insertPairs(String unitId, String description, String[][] words) {
            for (int i = 0; i < words.length; i++) {
                String[] pair = words[i];
                vDao.insertVocItem(new VocabularyItem(unitId, pair[1], pair[0], description));
            }
        }

    }

}
