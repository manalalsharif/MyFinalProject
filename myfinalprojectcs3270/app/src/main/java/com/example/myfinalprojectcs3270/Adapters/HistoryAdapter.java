package com.example.myfinalprojectcs3270.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final ArrayList<MyCartItem> historyList;
    Context context;

    public HistoryAdapter(ArrayList<MyCartItem> historyList, Context context) {
        this.context = context;
        this.historyList = historyList;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView purchaseDate, purchaseTitle, purchaseTotal;

        public HistoryViewHolder(View view) {
            super(view);
            purchaseDate = view.findViewById(R.id.txtDate);
            purchaseTitle = view.findViewById(R.id.txtTickets);
            purchaseTotal = view.findViewById(R.id.txtTotal);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row, null);

        Log.d("test", "SIZE: "+ historyList.size());
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.purchaseDate.setText(historyList.get(position).getDate());
        holder.purchaseTitle.setText(historyList.get(position).getTitle());
        holder.purchaseTotal.setText(String.valueOf(historyList.get(position).getTotal()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

}

