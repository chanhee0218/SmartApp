package com.example.suapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHelper {
    final static AdminActivity adminactivity=new AdminActivity();
    static String value=adminactivity.value;
    private static Retrofit retrofit;

    // TODO: replace with synchronized singleton

    public static Api getInstance(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(Api.class);

    }


}
