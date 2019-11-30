package com.example.myfinalprojectcs3270;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;


public class ConfirmPaymentActivity extends AppCompatActivity {

    private GifImageView gifImageViewConfirmed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_shopping_cart_white));

        gifImageViewConfirmed = (GifImageView) findViewById(R.id.gifFigmaView);
        try{
            InputStream inputStream = getAssets().open("gif_confirmed_payment.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageViewConfirmed.setBytes(bytes);
            gifImageViewConfirmed.startAnimation();
        }
        catch (IOException ex){
        }
    }
}
