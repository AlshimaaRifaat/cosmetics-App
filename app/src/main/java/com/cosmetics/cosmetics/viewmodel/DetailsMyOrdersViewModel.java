package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.MyOrdersDetailsData;
import com.cosmetics.cosmetics.model.MyOrdersDetailsResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMyOrdersViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<MyOrdersDetailsData>> listMyOrdersDetailsMutableLiveData;

    public LiveData<List<MyOrdersDetailsData>> getMyOrdersDetails(String order_id,String userTokenAuth, Context context) {

        listMyOrdersDetailsMutableLiveData = new MutableLiveData<List<MyOrdersDetailsData>>();
        this.context=context;
        getMyOrderDetailsValues(order_id,userTokenAuth);
        return listMyOrdersDetailsMutableLiveData;

    }

    private void getMyOrderDetailsValues(String order_id,String userTokenAuth) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("order_id", order_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MyOrdersDetailsResponse> call = apiInterface.getMyOrdersListDetails(hashMap,"Bearer "+userTokenAuth);
        call.enqueue(new Callback<MyOrdersDetailsResponse>() {
            @Override
            public void onResponse(Call<MyOrdersDetailsResponse> call, Response<MyOrdersDetailsResponse> response) {

                if (response.code()==200) {
                    listMyOrdersDetailsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listMyOrdersDetailsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MyOrdersDetailsResponse> call, Throwable t) {
                listMyOrdersDetailsMutableLiveData.setValue(null);

            }
        });
    }
}
