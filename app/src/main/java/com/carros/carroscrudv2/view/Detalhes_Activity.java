package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;
import com.carros.carroscrudv2.api.DataServices;
import com.carros.carroscrudv2.model.Carros;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detalhes_Activity extends AppCompatActivity {

    private ImageView imgLogoRoda;
    private TextView placa;
    private EditText modelo, ano;
    private Spinner marca;
    private ArrayAdapter<String> adaptador;

    private Retrofit retrofit;
    private DataServices service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //CAPTURANDO ID DOS COMPONENTES DA ACTIVITY
        placa = findViewById(R.id.txtDetalhesPlaca);
        modelo = findViewById(R.id.edtDetalhesModelo);
        ano = findViewById(R.id.edtDetalhesAno);
        marca = findViewById(R.id.spnDetalhesMarca);

        //SETANDO ANIMAÇÃO NA LOGOTIPO
        imgLogoRoda = findViewById(R.id.imageViewDetalhesRoda);
        Glide.with(this).load(R.drawable.roda).into(imgLogoRoda);

        //SETANDO BACKGROUND E DADOS NA SPINNER
        adaptador = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_text, listagemMarcas());
        adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        marca.setAdapter(adaptador);

        //RECEBENDO OBJETOS DA TELA LISTAGEM
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final Carros c = (Carros) b.getSerializable("dados");

        //SETANDO DADOS NA TELA DETALHES
        placa.setText(c.getPlaca());
        modelo.setText(c.getModelo());
        ano.setText(c.getAno());

        //PESQUISANDO A POSIÇÃO DA ARRAY PARA SETAR POSITION NO SPINNER
        int posicao = 0;
        for(int y=0; (y <= listagemMarcas().size()-1) ; y++){

            if(listagemMarcas().get(y).equals(c.getMarca())){

                posicao = y;
                break;
            }else{
                posicao = 0;
            }
        }
        marca.setSelection(posicao);


    }

    public void alterar(View v){
        //CONFIGURAÇÃO DO RETROFIT2
        retrofit = new Retrofit.Builder()
                .baseUrl("http://edilson.atwebpages.com/CrudCarrosV2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //DEVE SEMPRE FICAR ABAIXO DO BUILDER
        service = retrofit.create(DataServices.class);

        //CAPTURANDO DADOS DAS CAIXAS DE TEXTOS E SPINNER E CRIANDO UM OBJETO CARRO
        Carros carros = new Carros(marca.getSelectedItem().toString(), modelo.getText().toString(),
                placa.getText().toString(), ano.getText().toString());

        //RECUPERNADO O SERVIÇO CARROS E SALVA CARRO
        Call<Carros> call = service.alterarCarro(carros);

        call.enqueue(new Callback<Carros>() {
            @Override
            public void onResponse(Call<Carros> call, Response<Carros> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Veiculo alterado nos nossos banco de dados!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Listar_Activity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Carros> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ouve algum erro! X(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void excluir(){
        //CONFIGURAÇÃO DO RETROFIT2
        retrofit = new Retrofit.Builder()
                .baseUrl("http://edilson.atwebpages.com/CrudCarrosV2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //DEVE SEMPRE FICAR ABAIXO DO BUILDER
        service = retrofit.create(DataServices.class);

        //CAPTURANDO DADOS DAS CAIXAS DE TEXTOS E SPINNER E CRIANDO UM OBJETO CARRO
        Carros carros = new Carros(marca.getSelectedItem().toString(), modelo.getText().toString(),
                placa.getText().toString(), ano.getText().toString());

        //RECUPERNADO O SERVIÇO CARROS E SALVA CARRO
        Call<Carros> call = service.excluirCarro(carros);

        call.enqueue(new Callback<Carros>() {
            @Override
            public void onResponse(Call<Carros> call, Response<Carros> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Veiculo excluido dos nossos banco de dados!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Listar_Activity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Carros> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ouve algum erro! X(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sair(View v){
        startActivity(new Intent(getApplicationContext(), Listar_Activity.class));
        finish();
    }


    public ArrayList<String> listagemMarcas(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Audi");
        lista.add("BMW");
        lista.add("Cherry");
        lista.add("Chevrolet");
        lista.add("Citroen");
        lista.add("Dodge");
        lista.add("Ferrari");
        lista.add("Fiat");
        lista.add("Ford");
        lista.add("Hyundai");
        lista.add("Jac");
        lista.add("Jeep");
        lista.add("Peugeot");
        lista.add("Renault");
        lista.add("Mitsubish");
        lista.add("Suzuki");
        lista.add("Toyota");
        lista.add("Volkswagem");

        return lista;
    }

    public void excluirCarro(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tem certeza?");
        builder.setMessage("Você confirma a exclusão deste veículo?");

        builder.setPositiveButton("Sim!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir();
            }
        });
        builder.setNegativeButton("Não!", null);


        builder.show();
    }
}
