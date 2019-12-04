package com.example.myfinalprojectcs3270.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Adapters.CartAdapter;
import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.PaypalActivity;
import com.example.myfinalprojectcs3270.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.myfinalprojectcs3270.Fragments.CartDialog.cartModels;
import static com.example.myfinalprojectcs3270.PaypalActivity.total;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment {

    View root;

    public static TextView grandTotal;
    public static double grandTotalplus;
    public static ArrayList<MyCartItem> temparraylist;

    private RecyclerView cartRecycler;
    private List<MyCartItem> cartItemList;
    private CartAdapter cartAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button checkOut;

    Context context;

    public MyCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        getActivity().setTitle("MY CART");

        context = getContext();
        temparraylist = new ArrayList<>();
        checkOut = (Button) root.findViewById(R.id.checkout);
        grandTotal = root.findViewById(R.id.totalPrice);

        cartModels.addAll(temparraylist);

        Log.d("sizecart_1", String.valueOf(temparraylist.size()));
        Log.d("sizecart_2", String.valueOf(cartModels.size()));


        // from these lines of code we remove the duplicacy of cart and set last added quantity in cart
        // for replace same item
//        for (int i = 0; i < cartModels.size(); i++) {
//            for (int j = i + 1; j < cartModels.size(); j++) {
//                if (cartModels.get(i).getPoster_path().equals(cartModels.get(j).getPoster_path())) {
//                    cartModels.get(i).setQuantity(cartModels.get(j).getQuantity());
//                    cartModels.get(i).setTotal(cartModels.get(j).getTotal());
//                    cartModels.remove(j);
//                    j--;
//                }
//            }
//
//        }
        temparraylist.addAll(cartModels);
        cartModels.clear();
        Log.d("sizecart_11", String.valueOf(temparraylist.size()));
        Log.d("sizecart_22", String.valueOf(cartModels.size()));
        // this code is for get total cash
        for (int i = 0; i < temparraylist.size(); i++) {
            grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
        }
        grandTotal.setText("$" + String.valueOf(grandTotalplus));
        cartRecycler = root.findViewById(R.id.cart_recycler);
        cartAdapter = new CartAdapter(temparraylist, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        cartRecycler.setLayoutManager(mLayoutManager);
        cartRecycler.setAdapter(cartAdapter);


        //pay button
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = grandTotalplus;
                Intent intent = new Intent(getActivity(), PaypalActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
































