package com.example.khalessi.gretas_vokabeltrainer.exercise;

/**
 * Created by milde on 22.01.18.
 */

public interface IExercise {
    boolean checkResult(int selectedIdx);

    String[] getWords();

    String[] getNativeLang();

    int getSolutionIdx();

    void generateExercise();

}
