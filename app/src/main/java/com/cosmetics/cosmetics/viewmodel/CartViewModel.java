package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.DetailsProductAddCartResponse;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.GetListCartResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    Context context;
    private MutableLiveData<List<GetListCartData>> listCartMutableLiveData;

    public LiveData<List<GetListCartData>> getListCart(String lang,String user_token_Authorization , Context context) {

        listCartMutableLiveData = new MutableLiveData<List<GetListCartData>>();
            this.context=context;
            getListCartValues(lang,user_token_Authorization);

        return listCartMutableLiveData;

    }

    private void getListCartValues(String lang, String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GetListCartResponse> call = apiInterface.getListCart(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<GetListCartResponse>() {
            @Override
            public void onResponse(Call<GetListCartResponse> call, Response<GetListCartResponse> response) {

                if (response.code()==200) {
                    listCartMutableLiveData.setValue(response.body().getData().getList());
                } else  {
                    listCartMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetListCartResponse> call, Throwable t) {
                listCartMutableLiveData.setValue(null);

            }
        });
    }
}
