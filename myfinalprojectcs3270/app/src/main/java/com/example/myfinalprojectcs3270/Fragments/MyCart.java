package com.example.myfinalprojectcs3270.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment implements CartDialog.sendCartList {

    public static ArrayList<MyCartItem> cartItems = new ArrayList<>();

    public MyCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cart, container, false);
    }

    @Override
    public void addStuffToCart(MyCartItem newItem) {
        cartItems.add(newItem);
    }
}
