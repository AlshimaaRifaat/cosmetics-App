package com.cosmetics.cosmetics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cosmetics.cosmetics.model.LoginData;
import com.cosmetics.cosmetics.model.LoginResponse;
import com.cosmetics.cosmetics.model.User;
import com.cosmetics.cosmetics.remote.APIClient;
import com.cosmetics.cosmetics.remote.APIInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    Context context;
    private MutableLiveData<LoginData> mutableLiveData;
    String Error;

    public LiveData<LoginData> getLogin(User user, Context context) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<LoginData>();
            this.context = context;
            login(user);
        }
        return mutableLiveData;
    }

    public void login(User user) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("email", user.getEmail());
        queryMap.put("password", user.getPassword());
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LoginResponse> call = apiInterface.login(queryMap);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.code()==200)
                    {
                        mutableLiveData.setValue(response.body().getData());
                    }else if(response.code()==401)
                    {
                        mutableLiveData.setValue(null);
                    }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    mutableLiveData.setValue(null);
                    Error="error";
            }
        });

    }
    public String getErrorMsg() {
        return Error;
    }
}
