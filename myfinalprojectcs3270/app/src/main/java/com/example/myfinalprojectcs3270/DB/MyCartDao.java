package com.example.myfinalprojectcs3270.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfinalprojectcs3270.Fragments.MyCart;
import com.example.myfinalprojectcs3270.Object.MyCartItem;

import java.util.List;

@Dao
public interface MyCartDao {
    @Query("SELECT * FROM mycart")
    LiveData<List<MyCartItem>> loadAllPurchases();

    @Insert
    void insertIntoCart(MyCartItem myCartItem);
}
