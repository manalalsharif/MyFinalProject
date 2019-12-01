package com.example.myfinalprojectcs3270.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myfinalprojectcs3270.ConfirmPaymentActivity;
import com.example.myfinalprojectcs3270.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment {

    View root;
    private Button payconfirmed;

    public MyCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        payconfirmed = root.findViewById(R.id.pay);


        payconfirmed = (Button) root.findViewById(R.id.pay);
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
