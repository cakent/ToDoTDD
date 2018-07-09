package com.example.ckent.todotdd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

    @Dao
    interface TaskStore {
        @Query("SELECT * FROM tasks ORDER BY title")
        List<Tasks> selectAll();
        @Query("SELECT * FROM tasks WHERE id=:id")
        Tasks findById(String id);
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Tasks... tasks);
        @Update
        void update(Tasks... tasks);
        @Delete
        void delete(Tasks... tasks);
    }

