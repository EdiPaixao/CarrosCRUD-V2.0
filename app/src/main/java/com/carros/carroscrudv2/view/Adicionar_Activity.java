package com.carros.carroscrudv2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.carros.carroscrudv2.R;
import com.carros.carroscrudv2.api.DataServices;
import com.carros.carroscrudv2.model.Carros;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adicionar_Activity extends AppCompatActivity {

    private ImageView imgLogoRoda;
    private Spinner spnMarca;
    private EditText txtModelo, txtAno, txtPlaca;
    private ArrayAdapter<String> adaptador;

    private Retrofit retrofit;
    private DataServices service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        //RECUPARANDO AS ID DOS COMPONENTES
        imgLogoRoda = findViewById(R.id.imageViewDetalhesRoda);
        spnMarca = findViewById(R.id.spinnerAdicionarMarca);
        txtModelo = findViewById(R.id.edtAdicionarModelo);
        txtAno = findViewById(R.id.edtAdicionarAno);
        txtPlaca = findViewById(R.id.edtAdicionarPlaca);

        //SETANDO BACKGROUND E DADOS NA SPINNER
        adaptador = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_text, listagemMarcas());
        adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spnMarca.setAdapter(adaptador);


        //SETANDO MASCARA NO TEXTVIEW PLACA
        txtPlaca.setFilters( new InputFilter[] {new InputFilter.AllCaps ()});
        SimpleMaskFormatter simpleMaskPlaca= new SimpleMaskFormatter("UUU-NNNN");
        MaskTextWatcher maskMatricula = new MaskTextWatcher(txtPlaca, simpleMaskPlaca);
        txtPlaca.addTextChangedListener(maskMatricula);

        //CONFIGURAÇÃO DO RETROFIT2
        retrofit = new Retrofit.Builder()
                .baseUrl("http://edilson.atwebpages.com/CrudCarrosV2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //DEVE SEMPRE FICAR ABAIXO DO BUILDER
        service = retrofit.create(DataServices.class);



        Glide.with(this).load(R.drawable.roda).into(imgLogoRoda);
    }

    public void adicionarVeiculo(View v){

        String marca = spnMarca.getSelectedItem().toString();
        String modelo = txtModelo.getText().toString();
        String ano = txtAno.getText().toString();
        String placa = txtPlaca.getText().toString();


        //VERIFICAR SE OS CAMPOS ESTÃO VAZIOS
        if(modelo.isEmpty() || ano.isEmpty() || placa.isEmpty()){
            Toast.makeText(getApplicationContext(), "Favor preencher todos os dados.", Toast.LENGTH_LONG).show();
        } else{
            Carros carros = new Carros(marca, modelo, placa, ano);

            //RECUPERNADO O SERVIÇO CARROS E SALVA CARRO
            Call<Carros> call = service.salvarCarro(carros);

            call.enqueue(new Callback<Carros>() {
                @Override
                public void onResponse(Call<Carros> call, Response<Carros> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Veiculo cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    }
                }

                @Override
                public void onFailure(Call<Carros> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Ouve algum erro! X(", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void fecharAdicionar(View v){
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

    public void limpaCampos(){
        txtModelo.setText("");
        txtPlaca.setText("");
        txtAno.setText("");
    }
}
