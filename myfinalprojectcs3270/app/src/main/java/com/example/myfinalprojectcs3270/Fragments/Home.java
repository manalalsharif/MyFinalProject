package com.example.myfinalprojectcs3270.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfinalprojectcs3270.R;
import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View root;
    ViewFlipper v_flipper;
    private GifImageView gifImageView1;
    private GifImageView gifFigmaView_buy;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("HOUSE MOVIE");
        //Image arry for ImageFlipper
        int images[] = {R.drawable.s6, R.drawable.s2, R.drawable.s3,
                R.drawable.s5, R.drawable.s1, R.drawable.s7, R.drawable.s8, R.drawable.s9,R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15};


        //showing images in ImageFlipper
        v_flipper = root.findViewById(R.id.v_flipper);
        for(int image: images){
            flipperImages(image);

        }

        //Set Animatied picture
        gifImageView1 = (GifImageView) root.findViewById(R.id.gifFigmaView_buy);
        try{
            InputStream inputStream = getResources().getAssets().open("BuyCornburyTickets.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView1.setBytes(bytes);
            gifImageView1.startAnimation();


        }
        catch (IOException ex){
        }

        return root;
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000); // 3 sec
        v_flipper.setAutoStart(true);

        //set animation for images
        v_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);


        //Set onclick method when click on image go to popular movies fragment
        gifFigmaView_buy = root.findViewById(R.id.gifFigmaView_buy);

        gifFigmaView_buy.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v){
                FragmentTransaction fm = getFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container_main, new Popular());
                fm.commit();
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigationView.setSelectedItemId(R.id.menu_products);

            }
        });
    }
}
