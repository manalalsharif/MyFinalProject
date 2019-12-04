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
import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.PaypalActivity;
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
    private Button checkOut;

    public static TextView grandTotal;
    public static double grandTotalplus;
    public static ArrayList<MyCartItem> temparraylist;

    public MyCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        getActivity().setTitle("MY CART");
        //set up recycler view for fragment
        cartItemList = new ArrayList<>();



        //find and link up pay button
        checkOut = (Button) root.findViewById(R.id.checkout);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaypalActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
