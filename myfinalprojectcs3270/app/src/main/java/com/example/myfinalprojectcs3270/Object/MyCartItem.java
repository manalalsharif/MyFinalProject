package com.example.myfinalprojectcs3270.Object;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "mycart")
public class MyCartItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int myCart_id;
    private String title;
    private String date;
    private int quantity;
    private double price;
    private String poster_path;
    private double total;
    private boolean purchased;

    public MyCartItem(){
        this.price = 9.99;
    };

    public MyCartItem(int myCartId, String title) {
        this.myCart_id = myCartId;
        this.title = title;
        //defaults
        this.price = 9.99;
        this.date = date;
        this.quantity = 1;
        this.purchased = false;
    }

    @NonNull
    public int getMyCart_id() {
        return myCart_id;
    }

    public void setMyCart_id(@NonNull int myCart_id) {
        this.myCart_id = myCart_id;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}

