package com.example.myfinalprojectcs3270.Utilities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalprojectcs3270.MainActivity;
import com.example.myfinalprojectcs3270.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class HomeActivitySplash extends AppCompatActivity {

    private GifImageView gifImageView;
    private ProgressBar progressBar;
    private static int SPLASH_TIME_OUT = 4000; //4000 = 4 sec


    //Set GIFImageView resource
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_splash);

        gifImageView = (GifImageView) findViewById(R.id.gifFigmaView);

        try{
            InputStream inputStream = getAssets().open("logo_gif.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex){
        }

        //Wait for 4 seconds and start Activity Main(Home)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(HomeActivitySplash.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

