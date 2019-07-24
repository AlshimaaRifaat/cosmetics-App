package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestProductsViewModel extends ViewModel{
    Context context;
    private MutableLiveData<List<LatestProductsData>> listLatestProductsMutableLiveData;
    public LiveData<List<LatestProductsData>> getLatestProducts( String Lang, Context context) {
        if (listLatestProductsMutableLiveData == null) {
            listLatestProductsMutableLiveData = new MutableLiveData<List<LatestProductsData>>();
            this.context=context;
            getLatestProducts(Lang);
        }
        return listLatestProductsMutableLiveData;
    }

    private void getLatestProducts(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LatestProductsResponse> call = apiInterface.getLatestProducts(hashMap);
        call.enqueue(new Callback<LatestProductsResponse>() {
            @Override
            public void onResponse(Call<LatestProductsResponse> call, Response<LatestProductsResponse> response) {

                if (response.code()==200) {
                    listLatestProductsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listLatestProductsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LatestProductsResponse> call, Throwable t) {
                listLatestProductsMutableLiveData.setValue(null);

            }
        });
    }
}
