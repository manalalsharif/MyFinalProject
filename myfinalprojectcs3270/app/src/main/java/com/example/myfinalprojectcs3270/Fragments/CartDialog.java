package com.example.myfinalprojectcs3270.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class CartDialog extends DialogFragment {

    View root;
    private String movieId, name, image;
    private ImageView cartDecrement, cartIncrement, cartClose;
    private TextView updateQtyDialog, quantity;
    final int[] cartCounter = {0};

    public static ArrayList<MyCartItem> cartModels = new ArrayList<MyCartItem>();
    public static MyCartItem cartModel;


    public CartDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_cart_dialog, container, false);

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        //the second parameter is incase the pair for that key doesnt exist
        movieId = sp.getString("ID", "Error");
        name = sp.getString("NAME", "Error");
        image = sp.getString("IMAGE", "Error");

        cartDecrement = root.findViewById(R.id.cart_decrement);
        cartIncrement = root.findViewById(R.id.cart_increment);
        cartClose = root.findViewById(R.id.imageView_close_dialog_cart);
        updateQtyDialog = root.findViewById(R.id.update_quantity_dialog);
        quantity = root.findViewById(R.id.cart_product_quantity_tv);

        quantity.setText(String.valueOf(0));

        cartDecrement.setEnabled(false);
        cartDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartCounter[0] == 1) {
                    Toast.makeText(getContext(), "cant add less than 0", Toast.LENGTH_SHORT).show();
                } else {
                    cartCounter[0] -= 1;
                    quantity.setText(String.valueOf(cartCounter[0]));
                }

            }
        });

        cartIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDecrement.setEnabled(true);
                cartCounter[0] += 1;
                quantity.setText(String.valueOf(cartCounter[0]));
            }
        });

        updateQtyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new cart item
                cartModel = new MyCartItem();
                cartModel.setQuantity((cartCounter[0]));
                cartModel.setPoster_path(image);
                cartModel.setTitle(name);
                cartModel.setTotal(cartModel.getQuantity() * cartModel.getPrice());

                //adding movie
                cartModels.add(cartModel);

                //test
                Log.d("test", "ITEM ADDED IN CARTMODELS: " + cartModel.getQuantity() + " " +  cartModel.getTitle() + " movie(s).");

                dismiss();
            }

        });

        cartClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return root;
    }

}
