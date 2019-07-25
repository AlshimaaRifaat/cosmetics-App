package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.ProductCategoryData;
import com.cosmetics.cosmetics.model.ProductCategoryResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<ProductCategoryData>> listCategoryMutableLiveData;
    private MutableLiveData<List<ProductCategoryData>> listBrandMutableLiveData;
    public LiveData<List<ProductCategoryData>> getProductCategory(String Lang, Context context) {
        if (listCategoryMutableLiveData == null) {
            listCategoryMutableLiveData = new MutableLiveData<List<ProductCategoryData>>();
            this.context=context;
            getProductCategoryValues(Lang);

        }
        return listCategoryMutableLiveData;

    }
    public LiveData<List<ProductCategoryData>> getProductBrand(String Lang, Context context) {
        if (listBrandMutableLiveData == null) {
            listBrandMutableLiveData = new MutableLiveData<List<ProductCategoryData>>();
            this.context=context;
            getProductBrandValues(Lang);

        }
        return listBrandMutableLiveData;

    }

    private void getProductBrandValues(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ProductCategoryResponse> call = apiInterface.getProductBrand(hashMap);
        call.enqueue(new Callback<ProductCategoryResponse>() {
            @Override
            public void onResponse(Call<ProductCategoryResponse> call, Response<ProductCategoryResponse> response) {

                if (response.code()==200) {
                    listBrandMutableLiveData.setValue(response.body().getData());
                } else  {
                    listBrandMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductCategoryResponse> call, Throwable t) {
                listBrandMutableLiveData.setValue(null);

            }
        });
    }

    private void getProductCategoryValues(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ProductCategoryResponse> call = apiInterface.getProductCategory(hashMap);
        call.enqueue(new Callback<ProductCategoryResponse>() {
            @Override
            public void onResponse(Call<ProductCategoryResponse> call, Response<ProductCategoryResponse> response) {

                if (response.code()==200) {
                    listCategoryMutableLiveData.setValue(response.body().getData());
                } else  {
                    listCategoryMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductCategoryResponse> call, Throwable t) {
                listCategoryMutableLiveData.setValue(null);

            }
        });
    }
}
