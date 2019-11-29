package com.example.myfinalprojectcs3270.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.example.myfinalprojectcs3270.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
     View root;
     ViewFlipper v_flipper;


    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);

        int images[] = {R.drawable.s6, R.drawable.s2, R.drawable.s3,
                R.drawable.s5, R.drawable.s1, R.drawable.s7, R.drawable.s8, R.drawable.s9,R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15};

        v_flipper = root.findViewById(R.id.v_flipper);

        for(int image: images){
            flipperImages(image);
        }
        return root;
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000); // 3 sec
        v_flipper.setAutoStart(true);

        //set animation
        v_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

    }
}
