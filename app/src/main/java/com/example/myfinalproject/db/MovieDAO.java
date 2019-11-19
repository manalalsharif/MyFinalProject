package com.example.myfinalproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDAO {
    @Query("select * from Movie where adult = 0 and popularity >= 500")
    LiveData<List<Movie>> getAll();

    @Query("Select * from Movie where _id =:id")
    Movie getByID(int id);

    @Insert
    void insert (ArrayList<Movie> movie);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies (ArrayList<Movie> movies);

    @Insert
    void insert (Movie... movies);
}