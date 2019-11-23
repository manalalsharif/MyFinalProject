package com.example.myfinalprojectcs3270.Object;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "mycart",
        foreignKeys = @ForeignKey(entity = MovieItem.class,
        parentColumns = "id",
        childColumns = "movie_id",
        onDelete = CASCADE))
public class MyCartItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int myCart_id;
    @ColumnInfo(name = "movie_id")
    private String movie_id;
    private String title;
    private String date;
    private double price;

    public MyCartItem(){};

    public MyCartItem(int myCartId, String title) {
        this.myCart_id = myCartId;
        this.title = title;
        //defaults
        this.price = 9.99;
        this.date = date;
    }

    @NonNull
    public int getMyCart_id() {
        return myCart_id;
    }

    public void setMyCart_id(@NonNull int myCart_id) {
        this.myCart_id = myCart_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

