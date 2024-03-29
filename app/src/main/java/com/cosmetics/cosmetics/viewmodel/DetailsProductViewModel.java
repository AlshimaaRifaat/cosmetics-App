package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.CommentsData;
import com.cosmetics.cosmetics.model.CommentsResponse;
import com.cosmetics.cosmetics.model.DetailsProductAddCartResponse;
import com.cosmetics.cosmetics.model.DetailsProductColorsData;
import com.cosmetics.cosmetics.model.DetailsProductColorsResponse;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.DetailsProductSliderResponse;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.LatestProductsResponse;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProductViewModel extends ViewModel
{
    Context context;
    private MutableLiveData<List<DetailsProductSliderData>> listProductSliderMutableLiveData;
    private MutableLiveData<List<DetailsProductColorsData>> listProductColorsMutableLiveData;
    private MutableLiveData<DetailsProductAddCartResponse> productAddCartMutableLiveData;
    private MutableLiveData<PlusQuantityCartResponse> favoriteProductMutableLiveData;
    private MutableLiveData<List<CommentsData>> listCommentsMutableLiveData ;
    public LiveData<List<DetailsProductSliderData>> getDetailsProductSlider(String product_id, Context context) {
        if (listProductSliderMutableLiveData == null) {
            listProductSliderMutableLiveData = new MutableLiveData<List<DetailsProductSliderData>>();
            this.context=context;
            getDetailsProductSliderValues(product_id);

        }
        return listProductSliderMutableLiveData;

    }
    public LiveData<List<DetailsProductColorsData>> getDetailsProductColor(String product_id, Context context) {
        if (listProductColorsMutableLiveData== null) {
            listProductColorsMutableLiveData = new MutableLiveData<List<DetailsProductColorsData>>();
            this.context=context;
            getDetailsProductColorValues(product_id);

        }
        return listProductColorsMutableLiveData;

    }
    public LiveData<List<CommentsData>> getCommentsList(String product_id, Context context) {

        listCommentsMutableLiveData = new MutableLiveData<List<CommentsData>>();
            this.context=context;
            getCommentsListValues(product_id);

        return listCommentsMutableLiveData;

    }

    private void getCommentsListValues(String product_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", product_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CommentsResponse> call = apiInterface.getComments(hashMap);
        call.enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {

                if (response.code()==200) {
                    listCommentsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listCommentsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                listCommentsMutableLiveData.setValue(null);

            }
        });
    }

    public LiveData<DetailsProductAddCartResponse> getDetailsProductAddCart(String product_id,String product_quantity,String color_id,String user_token_authentication, Context context) {
        productAddCartMutableLiveData = new MutableLiveData<DetailsProductAddCartResponse>();
            this.context=context;
            getDetailsProductAddCartValues(product_id,product_quantity,color_id,user_token_authentication);

        return productAddCartMutableLiveData;

    }
    public LiveData<PlusQuantityCartResponse> getFavoriteProduct(String lang,String product_id,String user_token_authentication, Context context) {

        favoriteProductMutableLiveData = new MutableLiveData<PlusQuantityCartResponse>();
        this.context=context;
        getFavoriteProductValues(lang,product_id,user_token_authentication);

        return favoriteProductMutableLiveData;

    }

    private void getFavoriteProductValues(String lang, String product_id, String user_token_authentication) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("product_id",product_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlusQuantityCartResponse> call = apiInterface.getFavoriteProduct(hashMap,"Bearer "+user_token_authentication);
        call.enqueue(new Callback<PlusQuantityCartResponse>() {
            @Override
            public void onResponse(Call<PlusQuantityCartResponse> call, Response<PlusQuantityCartResponse> response) {

                if (response.code()==200) {
                    favoriteProductMutableLiveData.setValue(response.body());
                } else  {
                    favoriteProductMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlusQuantityCartResponse> call, Throwable t) {
                favoriteProductMutableLiveData.setValue(null);

            }
        });

    }

    private void getDetailsProductAddCartValues(String product_id,String product_quantity,String color_id,String user_token_authentication) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", product_id);
        hashMap.put("product_quantity",product_quantity);
        hashMap.put("color_id",color_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DetailsProductAddCartResponse> call = apiInterface.getDetailsProductAddCart(hashMap,"Bearer "+user_token_authentication);
        call.enqueue(new Callback<DetailsProductAddCartResponse>() {
            @Override
            public void onResponse(Call<DetailsProductAddCartResponse> call, Response<DetailsProductAddCartResponse> response) {

                if (response.code()==200) {
                    productAddCartMutableLiveData.setValue(response.body());
                } else  {
                    productAddCartMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DetailsProductAddCartResponse> call, Throwable t) {
                productAddCartMutableLiveData.setValue(null);

            }
        });

    }

    private void getDetailsProductColorValues(String product_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", product_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DetailsProductColorsResponse> call = apiInterface.getDetailsProductColors(hashMap);
        call.enqueue(new Callback<DetailsProductColorsResponse>() {
            @Override
            public void onResponse(Call<DetailsProductColorsResponse> call, Response<DetailsProductColorsResponse> response) {

                if (response.code()==200) {
                    listProductColorsMutableLiveData.setValue(response.body().getData());
                } else  {
                    listProductColorsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DetailsProductColorsResponse> call, Throwable t) {
                listProductColorsMutableLiveData.setValue(null);

            }
        });
    }

    private void getDetailsProductSliderValues(String product_id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_id", product_id);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DetailsProductSliderResponse> call = apiInterface.getDetailsProductSlider(hashMap);
        call.enqueue(new Callback<DetailsProductSliderResponse>() {
            @Override
            public void onResponse(Call<DetailsProductSliderResponse> call, Response<DetailsProductSliderResponse> response) {

                if (response.code()==200) {
                    listProductSliderMutableLiveData.setValue(response.body().getData());
                } else  {
                    listProductSliderMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DetailsProductSliderResponse> call, Throwable t) {
                listProductSliderMutableLiveData.setValue(null);

            }
        });
    }
}
