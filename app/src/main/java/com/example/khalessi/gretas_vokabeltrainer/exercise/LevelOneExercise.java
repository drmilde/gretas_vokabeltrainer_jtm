package com.example.khalessi.gretas_vokabeltrainer.exercise;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.Random;

/**
 * Created by milde on 22.01.18.
 */

public class LevelOneExercise {

    private Unit currentUnit;



    private void generateExercise() {
        currentUnit = AppState.getInstance().getDatabaseHelper().getUnit("GoingToSchool", true);
        currentUnit = AppState.getInstance().getCurrentUnit();

        Random ra = new Random();

        int idx = ra.nextInt(currentUnit.getVoclist().size());
        String firstWord = currentUnit.getVoclist().get(idx).getForeignLang();
        String solution = currentUnit.getVoclist().get(idx).getNativeLang();

        idx = ra.nextInt(currentUnit.getVoclist().size());
        String secondWord = currentUnit.getVoclist().get(idx).getForeignLang();

        idx = ra.nextInt(currentUnit.getVoclist().size());
        String thirdWord = currentUnit.getVoclist().get(idx).getForeignLang();

        idx = ra.nextInt(currentUnit.getVoclist().size());
        String forthWord = currentUnit.getVoclist().get(idx).getForeignLang();

        // setzen der Wörter in die TextViews der Activity

    }

}
