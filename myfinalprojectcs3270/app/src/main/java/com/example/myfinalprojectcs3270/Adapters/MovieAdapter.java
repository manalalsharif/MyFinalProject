package com.example.myfinalprojectcs3270.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.myfinalprojectcs3270.R;
import com.example.myfinalprojectcs3270.Object.MovieItem;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<MovieItem> movieItemList;

    private static final int LIST_ITEM = 100;
    private static final int GRID_ITEM = 101;
    private boolean isGrid = true;

    private RecyclerClickListener recyclerClickListener;

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;
        TextView movieName, movieOverview;

        public MovieViewHolder(View view) {
            super(view);
            moviePoster = view.findViewById(R.id.movie_poster);
            movieName = view.findViewById(R.id.movie_name);
            movieOverview = view.findViewById(R.id.movie_overview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            recyclerClickListener.onItemClicked(clickedPosition);
        }
    }

    public MovieAdapter(Context context, List<MovieItem> movieItemList, RecyclerClickListener recyclerClickListener) {
        this.context = context;
        this.movieItemList = movieItemList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == LIST_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, null);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_grid, null);
        }
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem movieItem = movieItemList.get(position);
        Picasso.get()
                .load(movieItem.getImage())
                .into(holder.moviePoster);

        holder.movieName.setText(movieItem.getName());

        holder.movieOverview.setText(movieItem.getOverview());
    }

    @Override
    public int getItemViewType(int position) {
        if (isGrid) {
            return GRID_ITEM;
        } else {
            return LIST_ITEM;
        }
    }

    public boolean toggleViewType() {
        isGrid = !isGrid;
        return isGrid;
    }

    @Override
    public int getItemCount() {
        return movieItemList.size();
    }

    public interface RecyclerClickListener {
        void onItemClicked(int clickedItemIndex);
    }
}
