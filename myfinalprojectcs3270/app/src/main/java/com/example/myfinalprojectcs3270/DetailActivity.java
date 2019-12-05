package com.example.myfinalprojectcs3270;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfinalprojectcs3270.DB.MovieDatabase;
import com.example.myfinalprojectcs3270.Fragments.CartDialog;
import com.example.myfinalprojectcs3270.Object.MovieItem;
import com.example.myfinalprojectcs3270.Utilities.AppExecutors;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    private LinearLayout detailParent;
    private Toolbar toolbar;
    private String movieID, name, image, overview, release, rating;
    private ImageView moviePoster;
    private TextView movieName, movieOverview, movieRating, movieRelease, movieFavorite;
    private Button addToCart;
    private MovieDatabase movieDatabase;
    private MovieItem favoriteMovie;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (intent != null) {
                getSupportActionBar().setTitle(intent.getExtras().getString("NAME"));
            }
        }

        //Getting the passed data from the intent.
        movieID = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        image = intent.getStringExtra("IMAGE");
        overview = intent.getStringExtra("OVERVIEW");
        release = intent.getStringExtra("RELEASE");
        rating = intent.getStringExtra("VOTE_AVG");

        //Initializing the views from the XML.
        initViews();

        //Checking if favorite movie
        isMovieFavorite();
        movieFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavoriteClicked();
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ID", movieID);
                editor.putString("NAME", name);
                editor.putString("IMAGE", image);
                editor.commit();


                FragmentManager fm = getSupportFragmentManager();
                CartDialog dialog = new CartDialog();
                fm.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, dialog)
                      //  .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void initViews() {
        moviePoster = findViewById(R.id.movie_poster_detail);
        movieName = findViewById(R.id.movie_name_detail);
        movieOverview = findViewById(R.id.movie_overview_detail);
        movieRating = findViewById(R.id.movie_rating_detail);
        movieRelease = findViewById(R.id.movie_release_detail);
        movieFavorite = findViewById(R.id.movie_favorite_detail);
        addToCart = findViewById(R.id.addToCart);

        Picasso.get()
                .load(image)
                .into(moviePoster);

        movieName.setText(name);
        movieOverview.setText(overview);
        movieRating.setText(rating);
        movieRelease.setText(release);

        movieDatabase = MovieDatabase.getInstance( getApplicationContext());
        detailParent = findViewById(R.id.detail_parent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }


    private void isMovieFavorite() {
        //Checking using the ID if the movie is present in the database and updating isFavorite variable.
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                favoriteMovie = movieDatabase.movieDao().loadMovieById(movieID);
                isFavorite = favoriteMovie != null;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Changing the background color of the text view with the state of isFavorite variable.
                        if (isFavorite) {
                            movieFavorite.setBackgroundResource(R.drawable.ic_favorite);
                        } else {
                            movieFavorite.setBackgroundResource(R.drawable.ic_favorite_border);
                        }
                    }
                });
            }
        });
    }

    private void onFavoriteClicked() {
        isMovieFavorite();

        if (isFavorite) {
            //Remove the movie from the favorites database.
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //Building the movie item so as to use when insertion or deletion takes place.
                    favoriteMovie = new MovieItem(movieID, name, image, overview, rating, release);
                    //Deleting the movie from the database.
                    movieDatabase.movieDao().deleteMovie(favoriteMovie);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            movieFavorite.setBackgroundResource(R.drawable.rm_from_favorites_icon);
                            movieFavorite.setBackgroundResource(R.drawable.ic_favorite_border);
                            isFavorite = false;
                            Log.d("ADebug", "Deleted");
                        }
                    });
                }
            });
        } else {
            //Add the movie to the favorites database.
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //Building the movie item so as to use when insertion or deletion takes place.
                    favoriteMovie = new MovieItem(movieID, name, image, overview, rating, release);
                    //Adding the movie to the database.
                    movieDatabase.movieDao().insertMovie(favoriteMovie);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            movieFavorite.setBackgroundResource(R.drawable.add_to_favorites_icon);
                            movieFavorite.setBackgroundResource(R.drawable.ic_favorite);
                            isFavorite = true;
                            Log.d("ADebug", "Inserted");
                        }
                    });
                }
            });
        }
    }

}