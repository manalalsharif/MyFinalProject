package com.example.myfinalprojectcs3270.Utilities;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfinalprojectcs3270.Object.MovieItem;
import com.example.myfinalprojectcs3270.DB.MovieDatabase;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private LiveData<List<MovieItem>> movieItemList;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        com.example.myfinalprojectcs3270.DB.MovieDatabase movieDatabase = com.example.myfinalprojectcs3270.DB.MovieDatabase.getInstance(this.getApplication());
        movieItemList = movieDatabase.movieDao().loadAllMovies();
    }

    public LiveData<List<MovieItem>> getMovieItemList() {
        return movieItemList;
    }
}
