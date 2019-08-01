package com.cosmetics.cosmetics.remote;

import com.cosmetics.cosmetics.model.DetailsProductAddCartResponse;
import com.cosmetics.cosmetics.model.DetailsProductColorsResponse;
import com.cosmetics.cosmetics.model.DetailsProductSliderResponse;
import com.cosmetics.cosmetics.model.GetListCartResponse;
import com.cosmetics.cosmetics.model.HomeSliderResponse;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.LoginResponse;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.model.ProductCategoryResponse;
import com.cosmetics.cosmetics.model.ProductsResponse;
import com.cosmetics.cosmetics.model.RegisterResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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

    @POST("slider")
    Call<HomeSliderResponse> getHomeSlider(@QueryMap Map<String, String> map);

    @POST("categories")
    Call<ProductCategoryResponse> getProductCategory(@QueryMap Map<String, String> map);

    @POST("brands")
    Call<ProductCategoryResponse> getProductBrand(@QueryMap Map<String, String> map);

    @POST("products")
    Call<ProductsResponse> getProducts(@QueryMap Map<String, String> map);

    @POST("products_images")
    Call<DetailsProductSliderResponse> getDetailsProductSlider(@QueryMap Map<String, String> map);

    @POST("products_colors")
    Call<DetailsProductColorsResponse> getDetailsProductColors(@QueryMap Map<String, String> map);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("add_cart")
    Call<DetailsProductAddCartResponse> getDetailsProductAddCart(@QueryMap Map<String, String> map, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("list_data_cart")
    Call<GetListCartResponse> getListCart(@QueryMap Map<String, String> map, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("plus_quentity_Cart")
    Call<PlusQuantityCartResponse> getPlusQuantityCart(@QueryMap Map<String, String> map, @Header("Authorization") String auth);
}
