package com.example.myfinalprojectcs3270.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Adapters.CartAdapter;
import com.example.myfinalprojectcs3270.ConfirmPaymentActivity;
import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment {

    View root;

    private RecyclerView cartRecycler;
    private List<MyCartItem> cartItemList;
    private CartAdapter cartAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button payconfirmed;


    public MyCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        //set up recycler view for fragment
        cartItemList = new ArrayList<>();



        //find and link up pay button
        payconfirmed = (Button) root.findViewById(R.id.btnPay);
        payconfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ConfirmPaymentActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
