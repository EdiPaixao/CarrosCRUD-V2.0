package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLogoRoda;
    private Button adicionar, listar, sobre, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLogoRoda = findViewById(R.id.imageViewDetalhesRoda);
        adicionar = findViewById(R.id.btnMainAdicionar);
        listar = findViewById(R.id.btnMainListar);
        sobre = findViewById(R.id.btnMainSobre);
        sair = findViewById(R.id.btnMainSair);

        Glide.with(this).load(R.drawable.roda).into(imgLogoRoda);


        //VERIFICANDO SE HÁ CONEXAO COM A INTERNET
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting()){
        } else {
            mensagemErro();
        }
    }

    public void telaSobre(View v){
        startActivity(new Intent(getApplicationContext(), Sobre_Activity.class));
    }

    public void sair(View v){
        finish();
    }

    public void adicionar(View v){
        startActivity(new Intent(getApplicationContext(), Adicionar_Activity.class));
    }

    public void listar(View v){
        startActivity(new Intent(getApplicationContext(), Listar_Activity.class));
    }

    public void mensagemErro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seu dispositivo não está conectado!");
        builder.setMessage("Verifique se o seu Wifi ou Redes Móveis estão em funcionamento.");

        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
