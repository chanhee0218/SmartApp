package com.example.suapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("")
    Call<ResponsePojo> uploadImage(
            @Field("En_Image") String encodeImage
    );
}
