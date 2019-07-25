package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.model.ProductsResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<ProductsData>> listProductsMutableLiveData;
    public LiveData<List<ProductsData>> getProducts(String brand_id,String category_id,String lang, Context context) {
        if (listProductsMutableLiveData == null) {
            listProductsMutableLiveData = new MutableLiveData<List<ProductsData>>();
            this.context=context;
            getProductsValues(brand_id,category_id,lang);

        }
        return listProductsMutableLiveData;

    }

    private void getProductsValues(String brand_id, String category_id, String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("brand_id", brand_id);
        hashMap.put("category_id", category_id);
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ProductsResponse> call = apiInterface.getProducts(hashMap);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                if (response.code()==200) {
                    listProductsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listProductsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                listProductsMutableLiveData.setValue(null);

            }
        });
    }


}
