package com.example.suapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getRetrofit {
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://20.55.17.29:5000/getImage/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}