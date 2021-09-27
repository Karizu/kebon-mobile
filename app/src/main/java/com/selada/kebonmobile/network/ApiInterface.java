package com.selada.kebonmobile.network;

import com.selada.kebonmobile.model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    String BASE_URL_PROD = "";
    String BASE_URL_DEV = "http://13.250.108.11:8081/api/";

    @Headers("Content-Type: application/json")
    @POST("auth/signup")
    Call<RegisterModel> register(@Body RegisterModel registerModel);
}
