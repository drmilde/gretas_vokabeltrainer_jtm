package com.example.khalessi.gretas_vokabeltrainer.exercise;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.Random;

/**
 * Created by milde on 22.01.18.
 */

public class LevelOneExercise implements IExercise {
    private Unit currentUnit;
    private String[] words = new String[4];
    private String[] nativeLang = new String[4];
    private int solutionIdx = 0;

    // random generator
    private Random ra = new Random();
    private int[] indizes = new int[4];

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

    /**
     * Check, if vocIdx is unique, thus avoiding
     * double entries in the quiz.
     *
     * @param vocIdx
     * @return true, if vocIdx is unique
     */
    private boolean unique(int vocIdx) {
        for (int idx = 0; idx < indizes.length; idx++) {
            if (indizes[idx] == vocIdx) {
                return false;
            }
        }
        return true;
    }


    // interface routinen

    /**
     * Check, if the selectedIdx is the solutionIdx
     *
     * @param selectedIdx
     * @return true, if selectedIdx == solutionIdx
     */
    @Override
    public boolean checkResult(int selectedIdx) {
        return (solutionIdx == selectedIdx);
    }

    // getter
    @Override
    public String[] getWords() {
        return words;
    }

    @Override
    public String[] getNativeLang() {
        return nativeLang;
    }

    @Override
    public int getSolutionIdx() {
        return solutionIdx;
    }

    /**
     * Generates a new exercise.
      */
    @Override
    public void generateExercise() {
        currentUnit = AppState.getInstance().getDatabaseHelper().getUnit("GoingToSchool", true);
        currentUnit = AppState.getInstance().getCurrentUnit();

        for (int idx = 0; idx < indizes.length; idx++) {
            getNextRandomVoc(idx);
        }
        solutionIdx = ra.nextInt(4);
    }
}
