package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.model.HomeSliderResponse;
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
    private MutableLiveData<List<LatestProductsData>> listFeaturedProductsMutableLiveData;
    private MutableLiveData<List<HomeSliderData>> listHomeSliderMutableLiveData;
    public LiveData<List<LatestProductsData>> getlatestProducts( String Lang,String userTokenAuth, Context context) {

            listLatestProductsMutableLiveData = new MutableLiveData<List<LatestProductsData>>();
            this.context=context;
            getLatestProductsValues(Lang,userTokenAuth);


        return listLatestProductsMutableLiveData;

    }
    public LiveData<List<LatestProductsData>> getFeaturedProducts( String Lang, Context context) {

            listFeaturedProductsMutableLiveData = new MutableLiveData<List<LatestProductsData>>();
            this.context=context;
            getFeaturedProductsValues(Lang);

        return listFeaturedProductsMutableLiveData;
    }
    public LiveData<List<HomeSliderData>> getHomeSlider( String Lang, Context context) {
        if (listHomeSliderMutableLiveData == null) {
            listHomeSliderMutableLiveData = new MutableLiveData<List<HomeSliderData>>();
            this.context=context;
            getHomeSliderValue(Lang);

        }
        return listHomeSliderMutableLiveData;

    }
    private void getLatestProductsValues(String lang,String userTokenAuth) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LatestProductsResponse> call = apiInterface.getLatestProducts(hashMap,"Bearer "+userTokenAuth);
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

    private void getFeaturedProductsValues(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LatestProductsResponse> call = apiInterface.getfeatureProducts(hashMap);
        call.enqueue(new Callback<LatestProductsResponse>() {
            @Override
            public void onResponse(Call<LatestProductsResponse> call, Response<LatestProductsResponse> response) {

                if (response.code()==200) {
                    listFeaturedProductsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listFeaturedProductsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LatestProductsResponse> call, Throwable t) {
                listFeaturedProductsMutableLiveData.setValue(null);

            }
        });
    }

    private void getHomeSliderValue(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<HomeSliderResponse> call = apiInterface.getHomeSlider(hashMap);
        call.enqueue(new Callback<HomeSliderResponse>() {
            @Override
            public void onResponse(Call<HomeSliderResponse> call, Response<HomeSliderResponse> response) {

                if (response.code()==200) {
                    listHomeSliderMutableLiveData.setValue(response.body().getData());
                } else  {
                    listHomeSliderMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<HomeSliderResponse> call, Throwable t) {
                listHomeSliderMutableLiveData.setValue(null);

            }
        });
    }
}
