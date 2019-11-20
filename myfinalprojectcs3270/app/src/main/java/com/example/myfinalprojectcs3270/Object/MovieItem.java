package com.example.myfinalprojectcs3270.Object;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieItem {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String image;
    private String overview;
    private String vote_average;
    private String release_date;

    private MovieItem() {
    }

    public MovieItem(@NonNull String id, String name, String image, String overview, String vote_average, String release_date) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }
}
