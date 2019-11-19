package com.example.myfinalproject;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfinalproject.db.AppDatabase;
import com.example.myfinalproject.db.Movie;

import java.util.List;

public class AllMoviesViewModel extends ViewModel {

    private LiveData<List<Movie>> movieList;

    public LiveData<List<Movie>> getMovieList(Context c){
        if (movieList != null)
            return movieList;

        return movieList = AppDatabase.getInstance(c).movieDAO().getAll();
    }

}
