package com.example.myfinalprojectcs3270.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.myfinalprojectcs3270.Object.MovieItem;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieItem>> loadAllMovies();

    @Insert
    void insertMovie(MovieItem movieItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(MovieItem movieItem);

    @Delete
    void deleteMovie(MovieItem movieItem);

    @Query("SELECT * FROM movies WHERE id = :movieID")
    MovieItem loadMovieById(String movieID);
}