package com.example.examen_frontend.login.model;

import static com.example.examen_frontend.service.ApiService.IP_BASE;

import com.example.examen_frontend.login.ContractLoginUser;
import com.example.examen_frontend.login.beans.User;
import com.example.examen_frontend.login.presenter.LoginUserPresenter;
import com.example.examen_frontend.login.utils.MyData;
import com.example.examen_frontend.service.ApiService;
import com.example.examen_frontend.service.RetrofitCliente;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginUserModel implements ContractLoginUser.Model {
    private LoginUserPresenter presenter;

    public LoginUserModel(LoginUserPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void loginAPI (User user, OnLoginUserListener onLoginUserListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "").create(ApiService.class);

        Call<MyData> call = apiService.login(user);
        call.enqueue(new Callback<MyData>() {
            @Override
            public void onResponse(Call<MyData> call, Response<MyData> response) {
                if (response.isSuccessful())
                {
                    MyData myData = response.body();
                    if (myData != null && myData.getUser() != null) {
                        onLoginUserListener.OnFinished(myData.getUser());
                    } else {
                        onLoginUserListener.OnFailure("Login failed: No usar data received");
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        onLoginUserListener.OnFailure("Login failed: " + jObjError.getString("error"));
                    } catch (Exception e) {
                        onLoginUserListener.OnFailure("Login failed: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyData> call, Throwable t) {
                onLoginUserListener.OnFailure("Network error: " + t.getMessage());
            }
        });
    }
}
