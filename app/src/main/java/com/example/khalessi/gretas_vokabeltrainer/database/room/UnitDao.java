package com.example.khalessi.gretas_vokabeltrainer.database.room;

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
    long insertUnit(Unit unit);

    @Update
    int updateUnit(Unit unit);

    @Delete
    int deleteUnit(Unit unit);

    @Query("DELETE FROM unit_table WHERE _id=:id")
    int deleteUnit(int id);

    @Query("DELETE FROM unit_table WHERE unitId=:unitId")
    int deleteUnit(String unitId);

    @Query("DELETE FROM unit_table")
    int deleteAllUnits();

    @Query("SELECT * FROM unit_table WHERE unitId=:unitId")
    Unit getUnit(String unitId);

    @Query("SELECT * from unit_table")
    List<Unit> getUnitsData();

}
