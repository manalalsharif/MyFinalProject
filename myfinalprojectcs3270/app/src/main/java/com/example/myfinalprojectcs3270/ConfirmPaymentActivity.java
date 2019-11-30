package com.example.myfinalprojectcs3270;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ConfirmPaymentActivity extends AppCompatActivity {

    private LinearLayout payParent;
    private Toolbar toolbar;
    private GifImageView gifFigmaViewConfirmed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_pay_has_confiremed);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Payment Confirmation");
        }


        gifFigmaViewConfirmed = (GifImageView) findViewById(R.id.gifFigmaViewConfirmed);
        try{
            InputStream inputStream = getAssets().open("gif_confirmed_payment.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifFigmaViewConfirmed.setBytes(bytes);
            gifFigmaViewConfirmed.startAnimation();
        }
        catch (IOException ex){
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}