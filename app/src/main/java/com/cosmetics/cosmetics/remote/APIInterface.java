package com.cosmetics.cosmetics.remote;

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
}
