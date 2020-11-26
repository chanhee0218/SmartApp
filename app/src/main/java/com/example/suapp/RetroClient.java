package com.example.suapp;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroClient {
    private static  String BASE_URL="http://036b942dadd7.ngrok.io/";
    private static RetroClient myclient;
    private Retrofit retrofit;
    RetroClient(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1200, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        BASE_URL=url;
        retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).client(okHttpClient).build();
    }
    public static synchronized RetroClient getInstance(){
        if(myclient==null){
            myclient=new RetroClient(BASE_URL);
        }
        return myclient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}

