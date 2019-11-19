package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;

public class Sobre_Activity extends AppCompatActivity {

    private Button sair;
    private ImageView imgLogoRoda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        sair = findViewById(R.id.btnSobreSair);
        imgLogoRoda = findViewById(R.id.imageViewDetalhesRoda);

        Glide.with(this).load(R.drawable.roda).into(imgLogoRoda);

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
