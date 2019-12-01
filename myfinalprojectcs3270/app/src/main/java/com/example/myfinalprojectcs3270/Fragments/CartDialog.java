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


/**
 * A simple {@link Fragment} subclass.
 */
public class CartDialog extends DialogFragment {

    View root;
    private String movieId, name, image;
    private ImageView cartDecrement, cartIncrement, cartClose;
    private TextView updateQtyDialog, quantity;
    final int[] cartCounter = {0};

    //create interface
    public interface sendCartList{
        void addStuffToCart(MyCartItem newItem);
    }

    //create listener
    private sendCartList listener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        listener = (sendCartList) activity;
    }


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
                MyCartItem cartItem = new MyCartItem();
                cartItem.setQuantity((cartCounter[0]));
                cartItem.setPoster_path(image);
                cartItem.setTitle(name);
                cartItem.setTotal(cartItem.getQuantity() * cartItem.getPrice());

                //calling listener that will receive the newly added cart item in the MyCart fragment
                listener.addStuffToCart(cartItem);
                //cartItems.add(cartItem);


                //test
                Log.d("test", "ITEM IN CART: " + cartItem.getTitle());

                //call listener and pass array list? which would be received in cart activity
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
