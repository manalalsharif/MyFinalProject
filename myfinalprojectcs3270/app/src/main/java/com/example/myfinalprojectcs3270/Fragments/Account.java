package com.example.myfinalprojectcs3270.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfinalprojectcs3270.HistoryActivity;
import com.example.myfinalprojectcs3270.R;
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    View root;
    private Button history;
    private GifImageView gifImageViewGoToHistory;
    private ImageView reOrder;

    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_account, container, false);

        getActivity().setTitle("MY ACCOUNT");
        history = root.findViewById(R.id.history);

        history = (Button) root.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);

            }
        });

        gifImageViewGoToHistory = (GifImageView) root.findViewById(R.id.click_here);
        try{
            InputStream inputStream = getResources().getAssets().open("gif_click_here1.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageViewGoToHistory.setBytes(bytes);
            gifImageViewGoToHistory.startAnimation();
        }
        catch (IOException ex){
        }

        //Set onclick method when click on image go to popular movies fragment
        reOrder = root.findViewById(R.id.reOrder);
        reOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container_main, new Popular());
                fr.commit();
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.menu_products);

            }
        });


        return root;
    }



}
