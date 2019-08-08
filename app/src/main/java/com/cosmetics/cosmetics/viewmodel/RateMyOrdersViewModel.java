package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateMyOrdersViewModel extends ViewModel {
    Context context;
    private MutableLiveData<PlusQuantityCartResponse> rateMyOrdersMutableLiveData;

    public LiveData<PlusQuantityCartResponse> getRateMyOrders(String product_id, String rate_range, String rate_comment ,String user_token_authorization,Context context) {

        rateMyOrdersMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getRateMyOrdersValues(product_id,rate_range,rate_comment,user_token_authorization);

        return rateMyOrdersMutableLiveData;

    }

    private void getRateMyOrdersValues(String product_id, String rate_range, String rate_comment,String user_token_authorization) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", product_id);
        hashMap.put("rate_range",rate_range);
        hashMap.put("rate_comment",rate_comment);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getRateMyOrdersNow(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    rateMyOrdersMutableLiveData.setValue(response.body());

                } else  {
                    rateMyOrdersMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                rateMyOrdersMutableLiveData.setValue(null);

            }
        });
    }

}
