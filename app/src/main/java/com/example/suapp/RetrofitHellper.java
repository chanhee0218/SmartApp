package com.example.suapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitHellper {
    final String Base_URL="http://20.55.17.29:5000/getImage/";
    @GET("/")
    Call<String> Auth();
}
