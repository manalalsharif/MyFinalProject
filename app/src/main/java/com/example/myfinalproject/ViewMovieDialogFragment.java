package com.example.myfinalproject;


import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfinalproject.db.AppDatabase;
import com.example.myfinalproject.db.Movie;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMovieDialogFragment extends DialogFragment {

    private ImageView viewPoster;
    private TextView viewTitle, viewYear;
    private View root;

    Movie movie;
    private int movie_pk;

    public ViewMovieDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_view_movie, container, false);

        //set up toolbar
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("Movie Details");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        viewPoster = (ImageView) root.findViewById(R.id.imageView);
        viewTitle = (TextView) root.findViewById(R.id.txtTitle);
        viewYear = (TextView) root.findViewById(R.id.txtYear);

        Bundle bundle = this.getArguments();
        movie_pk = bundle.getInt("movie_pk");
        new Thread(new Runnable() {
            @Override
            public void run() {
                movie = AppDatabase.getInstance(getContext())
                        .movieDAO()
                        .getByID(movie_pk);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get()
                                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                                .placeholder(R.drawable.image_placeholder)
                                .into(viewPoster);
                        viewTitle.setText(movie.getOriginal_title());
                        viewYear.setText(movie.getRelease_date());
                    }
                });
            }
        }).start();

        return root;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


}
