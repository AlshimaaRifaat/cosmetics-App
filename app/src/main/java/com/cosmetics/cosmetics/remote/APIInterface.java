package com.cosmetics.cosmetics.remote;

import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.LoginResponse;
import com.cosmetics.cosmetics.model.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIInterface {
    @POST("login")
    Call<LoginResponse> login(@QueryMap Map<String, String> map);

    @POST("register")
    Call<RegisterResponse> register(@QueryMap Map<String, String> map);

    @POST("latest_products")
    Call<LatestProductsResponse> getLatestProducts(@QueryMap Map<String, String> map);

    @POST("feature_products")
    Call<LatestProductsResponse> getfeatureProducts(@QueryMap Map<String, String> map);
}
