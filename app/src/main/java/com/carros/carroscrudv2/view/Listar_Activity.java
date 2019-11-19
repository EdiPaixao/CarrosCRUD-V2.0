package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;
import com.carros.carroscrudv2.adapter.CarroAdapter;
import com.carros.carroscrudv2.api.DataServices;
import com.carros.carroscrudv2.model.Carros;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Listar_Activity extends AppCompatActivity {

    private ImageView imgLogoRoda;
    private ListView lstListaCarros;
    private ArrayList<Carros> listaCarros = new ArrayList<>();
    private ArrayAdapter<Carros> adaptador;
    private Retrofit retrofit;
    private DataServices service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        imgLogoRoda = findViewById(R.id.imageViewDetalhesRoda);
        lstListaCarros = findViewById(R.id.lstListaCarros);

        Glide.with(this).load(R.drawable.roda).into(imgLogoRoda);

        //CONFIGURAÇÃO DO RETROFIT2
        retrofit = new Retrofit.Builder()
                .baseUrl("http://edilson.atwebpages.com/CrudCarrosV2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //DEVE SEMPRE FICAR ABAIXO DO BUILDER
        service = retrofit.create(DataServices.class);

        //RECUPERNADO O SERVIÇO CARROS E LISTA CARRO
        Call<ArrayList<Carros>> call = service.recuperarCarros();

        call.enqueue(new Callback<ArrayList<Carros>>() {
            @Override
            public void onResponse(Call<ArrayList<Carros>> call, Response<ArrayList<Carros>> response) {
                listaCarros = response.body();
                //SEMPRE DEVE-SE SETAR A LISTA DENTRO DO OVERRIDE
                lstListaCarros.setAdapter(new CarroAdapter(getApplicationContext(), listaCarros));

                lstListaCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Carros c = (Carros) lstListaCarros.getItemAtPosition(position);
                        Intent intent = new Intent(getApplicationContext(), Detalhes_Activity.class);

                        Bundle b = new Bundle();

                        b.putSerializable("dados", c);
                        intent.putExtras(b);
                        startActivity(intent);

                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<Carros>> call, Throwable t) {

            }
        });


    }

    public void sairListagem(View v){
        finish();
    }
}
