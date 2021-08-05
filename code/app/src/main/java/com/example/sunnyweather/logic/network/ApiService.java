package com.example.sunnyweather.logic.network;

import com.example.sunnyweather.logic.network.retrofit.RetrofitManager;

public class ApiService {
    public static <T> T create(final Class<T> service){

        return create(RetrofitManager.getsBaseUrl(),service);
    }

    public static <T> T create(String baseUrl,final Class<T> service){
        return RetrofitManager.get(baseUrl).create(service);
    }
}
