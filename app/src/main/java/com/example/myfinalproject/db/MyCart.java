package com.example.myfinalproject.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Movie.class,
        parentColumns = "_id",
        childColumns = "userId",
        onDelete = CASCADE))
public class MyCart {
    @PrimaryKey
    @NonNull
    private String myCartId;
    private String userId;
    private String title;
    private String date;
    private String posterPath;

    public MyCart(String myCartId, String title, String posterPath, String date) {
        this.myCartId = myCartId;
        this.title = title;
        this.posterPath = posterPath;
        this.date = date;
    }

    public String getMyCartId() {
        return myCartId;
    }

    public void setMyCartId(String myCartId) {
        this.myCartId = myCartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


}

