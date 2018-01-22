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
    private int solutionIdx = 0;

    // random generator
    private Random ra = new Random();
    private int[] indizes = new int[4];


    public void generateExercise() {
        currentUnit = AppState.getInstance().getDatabaseHelper().getUnit("GoingToSchool", true);
        currentUnit = AppState.getInstance().getCurrentUnit();

        for (int idx = 0; idx < indizes.length; idx++) {
            getNextRandomVoc(idx);
        }

        solutionIdx = ra.nextInt(4);
    }

    private void getNextRandomVoc(int idx) {
        // find unique word
        int vocIdx = ra.nextInt(currentUnit.getVoclist().size());
        while (!unique(vocIdx)) {
            vocIdx = ra.nextInt(currentUnit.getVoclist().size());
        }

        indizes[idx] = vocIdx;
        words[idx] = currentUnit.getVoclist().get(indizes[idx]).getForeignLang();
        nativeLang[idx] = currentUnit.getVoclist().get(indizes[idx]).getNativeLang();
    }

    private boolean unique(int vocIdx) {
        for (int idx = 0; idx < indizes.length; idx++) {
            if (indizes[idx] == vocIdx) {
                return false;
            }
        }
        return true;
    }

    public String[] getWords() {
        return words;
    }

    public String[] getNativeLang() {
        return nativeLang;
    }

    public int getSolutionIdx() {
        return solutionIdx;
    }
}
