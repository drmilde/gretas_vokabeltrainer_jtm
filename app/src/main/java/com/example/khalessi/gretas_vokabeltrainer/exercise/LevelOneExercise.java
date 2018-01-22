package com.example.khalessi.gretas_vokabeltrainer.exercise;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.Random;

/**
 * Created by milde on 22.01.18.
 */

public class LevelOneExercise {

    private Unit currentUnit;
    private String[] words = new String[4];
    private String[] nativeLang = new String[4];
    private String solution = "";
    private int solutionIdx = 0;


    public void generateExercise() {
        currentUnit = AppState.getInstance().getDatabaseHelper().getUnit("GoingToSchool", true);
        currentUnit = AppState.getInstance().getCurrentUnit();

        Random ra = new Random();

        int[] idx = new int[4];

        idx[0] = ra.nextInt(currentUnit.getVoclist().size());
        words[0] = currentUnit.getVoclist().get(idx[0]).getForeignLang();
        nativeLang[0] = currentUnit.getVoclist().get(idx[0]).getNativeLang();

        idx[1] = ra.nextInt(currentUnit.getVoclist().size());
        words[1] = currentUnit.getVoclist().get(idx[1]).getForeignLang();
        nativeLang[1] = currentUnit.getVoclist().get(idx[1]).getNativeLang();

        idx[2] = ra.nextInt(currentUnit.getVoclist().size());
        words[2] = currentUnit.getVoclist().get(idx[2]).getForeignLang();
        nativeLang[2] = currentUnit.getVoclist().get(idx[2]).getNativeLang();

        idx[3] = ra.nextInt(currentUnit.getVoclist().size());
        words[3] = currentUnit.getVoclist().get(idx[3]).getForeignLang();
        nativeLang[3] = currentUnit.getVoclist().get(idx[3]).getNativeLang();

        solutionIdx = ra.nextInt(4);

        // setzen der Wörter in die TextViews der Activity
    }

    public String[] getWords() {
        return words;
    }

    public String getSolution() {
        return solution;
    }

    public String[] getNativeLang() {
        return nativeLang;
    }

    public int getSolutionIdx() {
        return solutionIdx;
    }
}
