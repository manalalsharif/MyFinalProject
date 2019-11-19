package com.example.myfinalproject.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyCartDAO {
    @Query("select * from MyCart")
    LiveData<List<MyCart>> getAll();

    @Insert
    void insert (ArrayList<MyCart> entry);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies (ArrayList<MyCart> history);

    @Insert
    void insert (MyCart... purchases);
}
