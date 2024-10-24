package com.example.examen_frontend.service;

import com.example.examen_frontend.login.beans.User;
import com.example.examen_frontend.login.utils.MyData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    static final String IP_BASE = "192.168.0.38:3000";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("/login")
    Call<MyData> login(@Body User user);
}
