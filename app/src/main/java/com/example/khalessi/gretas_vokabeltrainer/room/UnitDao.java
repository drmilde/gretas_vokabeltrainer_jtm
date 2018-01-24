package com.example.khalessi.gretas_vokabeltrainer.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by milde on 24.01.18.
 */

@Dao
public interface UnitDao {

    @Insert
    void insertUnit(Unit unit);

    @Update
    void updateUnit(Unit unit);

    @Delete
    void deleteUnit(Unit unit);

    @Query("DELETE FROM unit_table")
    void deleteAllUnits();

    @Query("SELECT * FROM unit_table WHERE unitId=:unitId")
    Unit getUnit(String unitId);

    @Query("SELECT * from unit_table")
    List<Unit> getUnitsData();

}
