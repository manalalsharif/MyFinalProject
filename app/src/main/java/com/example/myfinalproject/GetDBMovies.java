package com.example.myfinalproject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myfinalproject.db.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetDBMovies extends AsyncTask<String, Integer, String> {

    private String rawJSON;

    private OnMovieListComplete mCallback;

    public interface OnMovieListComplete{
        void processMovieList(Movie[] movies);
    }

    public void setOnMovieListComplete(OnMovieListComplete listener){
        mCallback = listener;
    }


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url =  new URL("https://api.themoviedb.org/3/movie/popular?api_key=c088bdf1dd049eb86df61b6dcbb8b24b&language=en-US&page=1");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int status = connection.getResponseCode();
            switch (status) {
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    rawJSON = br.readLine();
                    Log.d("test", "doInBackground: " + rawJSON);
            }

        }
        catch(Exception e) {
            Log.d("test", "doInBackground: " + e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Movie[] movies;
        try {
            movies = parseJson(result);
            mCallback.processMovieList(movies);
        } catch (Exception e){
            Log.d("test", "onPostExecute: " + e.toString());
        }

    }

    private Movie[] parseJson(String result){
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        Movie[] movies = null;
        try {
             movies = gson.fromJson(rawJSON, Movie[].class);
        } catch (Exception e){
            Log.d("test", "parseJson: " + e.toString());
        }

        return movies;
    }
}
