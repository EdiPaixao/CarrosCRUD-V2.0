package com.carros.carroscrudv2.api;

import com.carros.carroscrudv2.model.Carros;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataServices {

    //ADICIONANDO CARROS
    @POST("adicionarCarros.php")
    Call<Carros> salvarCarro(@Body Carros carros);

    //RECUPERANDO A LISTA DE CARROS
    @GET("listarCarros.php")
    Call<ArrayList<Carros>> recuperarCarros();

    //EXCLUINDO CARRO
    @POST("excluirCarros.php")
    Call<Carros> excluirCarro(@Body Carros carros);

    //ALTERANDDO CARRO
    @POST("alterarCarros.php")
    Call<Carros> alterarCarro(@Body Carros carros);


}
