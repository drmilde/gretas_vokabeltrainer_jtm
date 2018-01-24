package com.example.khalessi.gretas_vokabeltrainer.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by milde on 24.01.18.
 */

@Database(entities = {Unit.class, VocabularyItem.class}, version = 1)
public abstract class GretasDatabase extends RoomDatabase {

    public abstract UnitDao unitDao();

    public abstract VocabularyItemDao vocabularyItemDao();

    private static GretasDatabase INSTANCE;

    static GretasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GretasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GretasDatabase.class, "gretas_database")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
