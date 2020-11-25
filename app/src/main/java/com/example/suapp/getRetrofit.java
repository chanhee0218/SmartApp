package com.example.suapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getRetrofit {
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://218.144.188.3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

}
}
