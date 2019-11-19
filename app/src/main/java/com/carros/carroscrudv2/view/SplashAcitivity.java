package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;


public class SplashAcitivity extends AppCompatActivity {

    private ImageView imgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLoading = findViewById(R.id.imageLoading);

        Glide.with(this).load(R.drawable.loading).into(imgLoading);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },2500);
    }
}
