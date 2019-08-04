package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.GetListCartResponse;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.ListFavoriteProductData;
import com.cosmetics.cosmetics.model.ListFavoriteProductResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFavoriteProductViewModel extends ViewModel{
    Context context;
    private MutableLiveData<List<ListFavoriteProductData>> listFavoritesMutableLiveData;
    public LiveData<List<ListFavoriteProductData>> getFavoriteProduct(String Lang,String user_token_Authorization, Context context) {

            listFavoritesMutableLiveData = new MutableLiveData<List<ListFavoriteProductData>>();
            this.context=context;
            getFavoriteProductValues(Lang,user_token_Authorization);

        return listFavoritesMutableLiveData;

    }

    private void getFavoriteProductValues(String lang,String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ListFavoriteProductResponse> call = apiInterface.getListFavoriteProduct(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<ListFavoriteProductResponse>() {
            @Override
            public void onResponse(Call<ListFavoriteProductResponse> call, Response<ListFavoriteProductResponse> response) {

                if (response.code()==200) {
                    listFavoritesMutableLiveData.setValue(response.body().getData());
                    listFavoritesMutableLiveData.setValue(response.body().getData());
                } else  {
                    listFavoritesMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ListFavoriteProductResponse> call, Throwable t) {
                listFavoritesMutableLiveData.setValue(null);

            }
        });
    }
}
