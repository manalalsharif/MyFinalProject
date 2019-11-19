package com.example.myfinalproject;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfinalproject.db.AppDatabase;
import com.example.myfinalproject.db.Movie;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private GetDBMovies task;
    private View root;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_product, container, false);
        task = new GetDBMovies();

        task.setOnMovieListComplete(new GetDBMovies.OnMovieListComplete() {
            @Override
            public void processMovieList(Movie[] movies) {
                final ArrayList<Movie> movieList = new ArrayList<>();
                for (Movie movie : movies) {
                    movieList.add(movie);
                }

                new Thread (new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase.getInstance(root.getContext())
                                .movieDAO()
                                .insert(movieList);
                    }
                }).start();
            }
        });
        task.execute("");
        return root;
    }

}
