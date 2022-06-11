package com.example.unigoiascrud;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(UserModal model);
    @Update
    void update(UserModal model);

    @Delete
    void delete(UserModal model);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("SELECT * FROM user_table ORDER BY userName ASC")
    LiveData<List<UserModal>> getAllUsers();
}