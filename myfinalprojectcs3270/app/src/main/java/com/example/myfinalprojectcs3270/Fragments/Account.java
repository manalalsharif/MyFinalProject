package com.example.myfinalprojectcs3270.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myfinalprojectcs3270.HistoryActivity;
import com.example.myfinalprojectcs3270.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    View root;
    private Button history;
    private GifImageView gifImageView1;

    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_account, container, false);

        history = root.findViewById(R.id.history);


        history = (Button) root.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);

            }
        });

        gifImageView1 = (GifImageView) root.findViewById(R.id.click_here);
        try{
            InputStream inputStream = getResources().getAssets().open("gif_click_here1.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView1.setBytes(bytes);
            gifImageView1.startAnimation();
        }
        catch (IOException ex){
        }

        return root;
    }
}
