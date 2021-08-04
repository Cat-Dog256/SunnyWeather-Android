package com.example.sunnyweather.logic.network;

import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<T>> {
    private Type responseType;
    private boolean isAPiResponse;

    public LiveDataCallAdapter(Type responseType, boolean isAPiResponse) {
        this.responseType = responseType;
        this.isAPiResponse = isAPiResponse;
    }

    @Override
    public Type responseType() {
        return null;
    }

    @Override
    public LiveData<T> adapt(Call<T> call) {
        return null;
    }
}
