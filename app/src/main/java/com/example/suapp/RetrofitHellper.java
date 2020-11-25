package com.example.suapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitHellper {
    final String Base_URL="http://3.35.16.85:20000/";
    @GET("/")
    Call<String> Auth();
}
