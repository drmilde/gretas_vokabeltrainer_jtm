package com.example.khalessi.gretas_vokabeltrainer.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandra Filbert on 04.01.18.
 */

public class UnitIdGenerator {


    public static String generate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy--HH-mm-ss");
        Date date = new Date();
        //2016/11/16 12:08:43
        return dateFormat.format(date);
    }

}
