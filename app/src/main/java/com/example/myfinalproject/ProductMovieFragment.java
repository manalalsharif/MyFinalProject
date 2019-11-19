package com.example.myfinalproject;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalproject.db.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductMovieFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdaper movieRecyclerViewAdaper;
    private int columnCount = 1;

    public ProductMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_product_movie, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        return root;
    }

    @Override
    public void onResume(){
        super.onResume();

        Context context = getContext();
        movieRecyclerViewAdaper = new MovieRecyclerViewAdaper(new ArrayList<Movie>());

        if (columnCount <= 1){
            //based on how many items are in the view we can change it to set a different kind of layout
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(movieRecyclerViewAdaper);
        //very important or else you will have display errors
        recyclerView.setHasFixedSize(false);

        ViewModelProviders.of(this)
                .get(AllMoviesViewModel.class)
                .getMovieList(context)
                .observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        if (movies != null){
                            movieRecyclerViewAdaper.addItems(movies);
                        }
                    }
                });
    }
}
