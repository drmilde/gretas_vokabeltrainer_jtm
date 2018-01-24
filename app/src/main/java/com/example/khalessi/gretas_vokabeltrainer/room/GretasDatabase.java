package com.example.khalessi.gretas_vokabeltrainer.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by milde on 24.01.18.
 */

@Database(entities = {Unit.class, VocabularyItem.class}, version = 1)
public abstract class GretasDatabase extends RoomDatabase {

    private static String DB_NAME = "gretas_database";

    public abstract UnitDao unitDao();

    public abstract VocabularyItemDao vocabularyItemDao();

    private static GretasDatabase INSTANCE;

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
                        .addCallback(sRoomDatabaseCallback)
                        .build();

            }
        }
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

        PopulateDbAsync(GretasDatabase db) {
            uDao = db.unitDao();
            vDao = db.vocabularyItemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            uDao.deleteAllUnits();
            uDao.insertUnit(new Unit("GoingToSchool", "Greta", "Going to school", "in school"));
            uDao.insertUnit(new Unit("GoingToSchool", "Greta", "Going to school", "in school"));
            uDao.insertUnit(new Unit("Kitchen", "Greta", "Kitchen", "cooking and baking"));
            uDao.insertUnit(new Unit("Animals", "Greta", "Animals", "living creatures"));
            uDao.insertUnit(new Unit("Oceans", "Greta", "Oceans", "the oceans of the world"));
            uDao.insertUnit(new Unit("Asking questions", "Greta", "Asking questions", "Asking questions"));
            uDao.insertUnit(new Unit("say hello", "Greta", "Greetings", "how to say hello"));
            uDao.insertUnit(new Unit("inside the zoo", "Greta", "The zoo", "inside the zoo"));
            uDao.insertUnit(new Unit("merry christmas", "Greta", "Christmas", "have a merry little christmas"));
            uDao.insertUnit(new Unit("at the beach", "Greta", "At the beach", "at the beach"));
            uDao.insertUnit(new Unit("suitcase", "Greta", "Suitcase", "inside my suitcase"));
            uDao.insertUnit(new Unit("The body", "Greta", "Body parts", "body parts"));
            uDao.insertUnit(new Unit("getting dressed", "Greta", "Clothes", "getting dressed"));
            uDao.insertUnit(new Unit("bedroom", "Greta", "Bedroom", "the bedroom"));
            uDao.insertUnit(new Unit("bath room", "Greta", "Bathroom", "the bath room"));
            uDao.insertUnit(new Unit("the house", "Greta", "House", "inside a house"));
            uDao.insertUnit(new Unit("food", "Greta", "Food", "something to eat"));
            uDao.insertUnit(new Unit("colors", "Greta", "colors", "color up your life"));
            uDao.insertUnit(new Unit("months", "Greta", "Months", "months of the year"));
            uDao.insertUnit(new Unit("drinks", "Greta", "Drinks", "something to drink"));
            uDao.insertUnit(new Unit("numbers", "Greta", "Numbers", "counting is fun"));
            uDao.insertUnit(new Unit("tools", "Greta", "Tools", "tools and the work shop"));
            uDao.insertUnit(new Unit("school subject", "Greta", "School subjects", "topics you learn at school"));
            uDao.insertUnit(new Unit("school bag", "Greta", "School bag", "things, you need at school"));
            uDao.insertUnit(new Unit("traffic", "Greta", "Traffic", "traffic"));

            return null;
        }

    }

}
