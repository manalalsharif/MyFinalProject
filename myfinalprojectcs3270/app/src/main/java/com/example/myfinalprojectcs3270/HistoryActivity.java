package com.example.myfinalprojectcs3270;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Adapters.HistoryAdapter;
import com.example.myfinalprojectcs3270.Object.MyCartItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    public static ArrayList<MyCartItem> historyArrayList = new ArrayList<MyCartItem>();

    private RecyclerView historyRecycler;
    private List<MyCartItem> historyList;
    private HistoryAdapter historyAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private LinearLayout historyParent;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Your Purchased History");
        }

        historyRecycler = (RecyclerView) findViewById(R.id.history_recycler);
        historyAdapter = new HistoryAdapter(historyArrayList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        historyRecycler.setLayoutManager(mLayoutManager);
        historyRecycler.setAdapter(historyAdapter);

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

}
