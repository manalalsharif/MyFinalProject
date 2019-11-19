package com.example.myfinalproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Movie.class, MyCart.class}, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance != null) return instance;

        instance = Room.databaseBuilder(context, AppDatabase.class,"movie-database")
                .build();
        return instance;
    }

    public abstract MovieDAO movieDAO();
    public abstract MyCartDAO myCartDAO();
}
