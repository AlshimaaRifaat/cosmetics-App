package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.MyOrdersData;
import com.cosmetics.cosmetics.model.MyOrdersResponse;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.model.ProductsResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<MyOrdersData>> listMyOrdersMutableLiveData;
    public LiveData<List<MyOrdersData>> getMyOrders(String lang, String user_token_authorization, Context context) {

            listMyOrdersMutableLiveData = new MutableLiveData<List<MyOrdersData>>();
            this.context=context;
        getMyOrdersValues(lang,user_token_authorization);

        return listMyOrdersMutableLiveData;

    }

    private void getMyOrdersValues(String lang,String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MyOrdersResponse> call = apiInterface.getMyOrders(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<MyOrdersResponse>() {
            @Override
            public void onResponse(Call<MyOrdersResponse> call, Response<MyOrdersResponse> response) {

                if (response.code()==200) {
                    listMyOrdersMutableLiveData.setValue(response.body().getData());

                } else  {
                    listMyOrdersMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MyOrdersResponse> call, Throwable t) {
                listMyOrdersMutableLiveData.setValue(null);

            }
        });
    }
}
