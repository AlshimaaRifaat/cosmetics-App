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

public class OrderNowViewModel extends ViewModel {
    Context context;
    private MutableLiveData<PlusQuantityCartResponse> orderNowMutableLiveData;
    public LiveData<PlusQuantityCartResponse> getOrderNow(String order_total_price, String customer_address,
                                                          String customer_phone ,String langtude,String lattude,
                                                          String payment_method,String payment_status,
                                                          String customer_comments_extra,String customer_city,
                                                          String customer_country,String customer_street,
                                                          String customer_postal_code,
                                                          String user_token_authorization,Context context) {

        orderNowMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getorderNowValues(order_total_price,customer_address,customer_phone,langtude,lattude,
                payment_method,payment_status,customer_comments_extra,customer_city,
                customer_country,customer_street,customer_postal_code,user_token_authorization);

        return orderNowMutableLiveData;

    }

    private void getorderNowValues(String order_total_price, String customer_address,
                                   String customer_phone, String langtude,
                                   String lattude, String payment_method,
                                   String payment_status, String customer_comments_extra,
                                   String customer_city, String customer_country,
                                   String customer_street, String customer_postal_code,String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("order_total_price", order_total_price);
        hashMap.put("customer_address",customer_address);
        hashMap.put("customer_phone",customer_phone);
        hashMap.put("langtude",langtude);
        hashMap.put("lattude",lattude);
        hashMap.put("payment_method",payment_method);
        hashMap.put("payment_status",payment_status);
        hashMap.put("customer_comments_extra",customer_comments_extra);
        hashMap.put("customer_city",customer_city);
        hashMap.put("customer_country",customer_country);
        hashMap.put("customer_street",customer_street);
        hashMap.put("customer_postal_code",customer_postal_code);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getOrderNow(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    orderNowMutableLiveData.setValue(response.body());

                } else  {
                    orderNowMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                orderNowMutableLiveData.setValue(null);

            }
        });
    }
}
