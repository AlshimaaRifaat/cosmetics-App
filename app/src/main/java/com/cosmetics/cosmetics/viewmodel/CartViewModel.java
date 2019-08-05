package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.DetailsProductAddCartResponse;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.GetListCartResponse;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.model.TotalResultGetListCartData;
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
    private MutableLiveData<TotalResultGetListCartData> totalResultCartMutableLiveData;
    private MutableLiveData<PlusQuantityCartResponse> plusQuantityCartMutableLiveData;
    private MutableLiveData<PlusQuantityCartResponse> minQuantityCartMutableLiveData;
    private MutableLiveData<PlusQuantityCartResponse> deleteItemCartMutableLiveData;
    public LiveData<List<GetListCartData>> getListCart(String lang,String user_token_Authorization , Context context) {

        listCartMutableLiveData = new MutableLiveData<List<GetListCartData>>();
            this.context=context;
            getListCartValues(lang,user_token_Authorization);

        return listCartMutableLiveData;

    }

    public LiveData<TotalResultGetListCartData> getTotalResultListCart(String lang,String user_token_Authorization , Context context) {

        totalResultCartMutableLiveData = new MutableLiveData<TotalResultGetListCartData>();
        this.context=context;
        getListCartValues(lang,user_token_Authorization);

        return totalResultCartMutableLiveData;

    }

    public LiveData<PlusQuantityCartResponse> getPlusQuantityCart(String lang,String cart_id,String user_token_Authorization , Context context) {

        plusQuantityCartMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getPlusQuantityCartValues(lang,cart_id,user_token_Authorization);

        return plusQuantityCartMutableLiveData;

    }
    public LiveData<PlusQuantityCartResponse> getMinQuantityCart(String lang,String cart_id,String user_token_Authorization , Context context) {

        minQuantityCartMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getMinQuantityCartValues(lang,cart_id,user_token_Authorization);

        return minQuantityCartMutableLiveData;

    }
    public LiveData<PlusQuantityCartResponse> getDeleteItemCart(String lang,String cart_id,String user_token_Authorization , Context context) {

        deleteItemCartMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getDeleteItemCartValues(lang,cart_id,user_token_Authorization);

        return deleteItemCartMutableLiveData;

    }

    private void getDeleteItemCartValues(String lang, String cart_id, String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("cart_id",cart_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getDeleteItemCart(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    deleteItemCartMutableLiveData.setValue(response.body());

                } else  {
                    deleteItemCartMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                deleteItemCartMutableLiveData.setValue(null);

            }
        });
    }

    private void getMinQuantityCartValues(String lang, String cart_id, String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("cart_id",cart_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getMinQuantityProduct(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    minQuantityCartMutableLiveData.setValue(response.body());

                } else  {
                    minQuantityCartMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                minQuantityCartMutableLiveData.setValue(null);

            }
        });
    }

    private void getPlusQuantityCartValues(String lang, String cart_id, String user_token_authorization) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("cart_id",cart_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getPlusQuantityCart(hashMap,"Bearer "+user_token_authorization);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    plusQuantityCartMutableLiveData.setValue(response.body());

                } else  {
                    plusQuantityCartMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                plusQuantityCartMutableLiveData.setValue(null);

            }
        });
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
                    totalResultCartMutableLiveData.setValue(response.body().getData());
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
