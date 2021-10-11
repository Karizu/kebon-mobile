package com.selada.kebonmobile.network;

import com.selada.kebonmobile.model.LoginModel;
import com.selada.kebonmobile.model.RegisterModel;
import com.selada.kebonmobile.model.response.ApiResponse;
import com.selada.kebonmobile.model.response.LoginResponse;
import com.selada.kebonmobile.model.response.RegisterResponse;
import com.selada.kebonmobile.model.response.SiteLeasableResponse;
import com.selada.kebonmobile.model.response.SiteResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    String BASE_URL_PROD = "http://13.250.108.11:8081/v0/";
    String BASE_URL_DEV = "http://13.250.123.226:8082/v0/";

    @Headers("Content-Type: application/json")
    @POST("auth/register")
    Call<ApiResponse<LoginResponse>> register(@Body RegisterModel registerModel);

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<ApiResponse<LoginResponse>> signIn(@Body LoginModel loginModel);

    @GET("site")
    Call<ApiResponse<List<SiteResponse>>> getListSite(@Header("Authorization") String token);

    @GET("site/leasable")
    Call<ApiResponse<List<SiteLeasableResponse>>> getListSiteLeasable(@Header("Authorization") String token);
}
