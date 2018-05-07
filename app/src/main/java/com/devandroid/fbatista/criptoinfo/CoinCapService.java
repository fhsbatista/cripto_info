package com.devandroid.fbatista.criptoinfo;

import com.devandroid.fbatista.criptoinfo.model.CryptoCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinCapService {


    public static final String BASE_URL = "https://api.coinmarketcap.com/";


    @GET("/v1/ticker/")
    Call<List<CryptoCurrency>> getCryptoCurrency(@Query("limit") int limite);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();




}
