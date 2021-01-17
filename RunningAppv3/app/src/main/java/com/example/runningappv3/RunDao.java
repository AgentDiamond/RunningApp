package com.example.runningappv3;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RunDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Run run);

    @Delete
    void deleteRun(Run run);

    @Query("DELETE FROM run_table")
    void deleteAll();

    @Query("SELECT * FROM run_table ORDER BY id ASC")
    LiveData<List<Run>> getRunById();

    @Query("SELECT * FROM run_table ORDER BY distance ASC")
    LiveData<List<Run>> getRunByDistance();

    @Query("SELECT * FROM run_table ORDER BY time ASC")
    LiveData<List<Run>> getRunByTime();

    @Query("SELECT * FROM run_table ORDER BY speed ASC")
    LiveData<List<Run>> getRunBySpeed();


}