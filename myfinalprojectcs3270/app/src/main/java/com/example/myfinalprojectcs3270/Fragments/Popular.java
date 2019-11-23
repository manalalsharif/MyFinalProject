package com.example.myfinalprojectcs3270.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfinalprojectcs3270.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.myfinalprojectcs3270.Adapters.MovieAdapter;
import com.example.myfinalprojectcs3270.Object.MovieItem;
import com.example.myfinalprojectcs3270.R;
import com.example.myfinalprojectcs3270.DetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class Popular extends Fragment implements MovieAdapter.RecyclerClickListener {

    private static String NOW_PLAYING;

    private RecyclerView moviesRecycler;
    private List<MovieItem> movieItemList;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    private static final String LIFECYCLE_CALLBACK_KEY = "callbacks";
    private Bundle state;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Setting the common layout for the fragment.
        View view = inflater.inflate(R.layout.fragment_movies_layout, container, false);

        //Initialising the URL parameter from the strings.xml file.
        NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=c088bdf1dd049eb86df61b6dcbb8b24b&amp;language=en-US&page=1&amp;region=US";

        //Setting up the recycler view for the fragment.
        movieItemList = new ArrayList<>();
        moviesRecycler = view.findViewById(R.id.movies_recycler);
        movieAdapter = new MovieAdapter(this.getContext().getApplicationContext(), movieItemList, this);

        layoutManager = new GridLayoutManager(this.getActivity(), numberOfColumns());
        moviesRecycler.setLayoutManager(layoutManager);
        moviesRecycler.setItemAnimator(new DefaultItemAnimator());
        moviesRecycler.setNestedScrollingEnabled(false);
        moviesRecycler.setHasFixedSize(true);
        moviesRecycler.setAdapter(movieAdapter);

        //Checking if there is network connection and making requests if connected.
        if (isConnected()) {
            networkCalls();
            state = savedInstanceState;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (state != null && state.containsKey(LIFECYCLE_CALLBACK_KEY)) {
                        int visiblePos = state.getInt(LIFECYCLE_CALLBACK_KEY);
                        if (visiblePos < 0) {
                            visiblePos = 0;
                        }
                        moviesRecycler.smoothScrollToPosition(visiblePos);
                    }
                }
            }, 500);
        } else {
            Toast.makeText(getContext(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onItemClicked(int clickedItemIndex) {
        //Getting the information about the item clicked.
        MovieItem movieItem;
        Intent detailedView = new Intent(getContext().getApplicationContext(), DetailActivity.class);
        movieItem = movieItemList.get(clickedItemIndex);

        Bundle bundle = new Bundle();
        //Passing in the data to the detailed activity.
        bundle.putString("ID", movieItem.getId());
        bundle.putString("NAME", movieItem.getName());
        bundle.putString("IMAGE", movieItem.getImage());
        bundle.putString("OVERVIEW", movieItem.getOverview());
        bundle.putString("RELEASE", movieItem.getRelease_date());
        bundle.putString("VOTE_AVG", movieItem.getVote_average());

        detailedView.putExtras(bundle);

        //Starting the activity with all the data passed to the next one.
        startActivity(detailedView);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    private void extractFromJSON(JSONObject baseJSONResponse) {
        try {
            JSONArray results = baseJSONResponse.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movieObject = results.getJSONObject(i);

                String id = movieObject.getString("id");
                String vote_average = movieObject.getString("vote_average");
                String poster_path = movieObject.getString("poster_path");
                String name = movieObject.getString("original_title");
                String overview = movieObject.getString("overview");
                String release_date = movieObject.getString("release_date");
                //testing to see if an entire image url will load
                //String image = "https://m.media-amazon.com/images/M/MV5BYmU4NTk4OWYtMjE4My00MGVkLTgwY2EtZTZjN2YyOGFiMDQ0L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg";
                String image = "https://image.tmdb.org/t/p/w500/" + poster_path;

                movieItemList.add(new MovieItem(id, name, image, overview, vote_average, release_date));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        movieAdapter.notifyDataSetChanged();
    }

    private void networkCalls() {
        requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        if (isConnected()) {
            movieItemList.clear();
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, NOW_PLAYING, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            extractFromJSON(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext().getApplicationContext(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        requestQueue.add(jsonObjectRequest);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int currentPos = ((GridLayoutManager) moviesRecycler.getLayoutManager()).findFirstVisibleItemPosition();
        outState.putInt(LIFECYCLE_CALLBACK_KEY, currentPos);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
