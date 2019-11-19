package com.example.myfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalproject.db.Movie;

import java.util.List;

public class MovieRecyclerViewAdaper extends RecyclerView.Adapter<MovieRecyclerViewAdaper.ViewHolder> {
    private final List<Movie> movies;

    public MovieRecyclerViewAdaper(List<Movie> movies) {
        this.movies = movies;
    }

    public void addItems(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void clear(){
        this.movies.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtLine1, txtLine2, txtLine3;
        public View root;
        public Movie movie;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            root = itemView;

            txtLine1 = (TextView)root.findViewById(R.id.t1);
            txtLine2 = (TextView)root.findViewById(R.id.t2);

        }
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdaper.ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        if (movie != null){
            holder.movie = movie;
            holder.txtLine1.setText(movie.getOriginal_title());
            holder.txtLine2.setText(movie.getRelease_date());

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("movie_pk", movie.get_id());

                    ViewMovieDialogFragment new_fragment = new ViewMovieDialogFragment();
                    new_fragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .add(android.R.id.content, new_fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
