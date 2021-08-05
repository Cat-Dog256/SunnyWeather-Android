package com.example.sunnyweather.logic.network.retrofit;

import android.util.Log;

import com.example.sunnyweather.logic.network.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<T>> {
    private Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<T> adapt(final Call<T> call) {
        return new MyLiveDta<T>(call);
    }
    private static class MyLiveDta<T> extends LiveData<T>{
        //一般情况下，我们使用 AtomicBoolean 高效并发处理 “只初始化一次” 的功能要求：
        private AtomicBoolean start = new AtomicBoolean(false);
        private final Call<T> call;

        public MyLiveDta(Call<T> call) {
            this.call = call;
        }

        @Override
        protected void onActive() {
            super.onActive();
            if (start.compareAndSet(false,true)){
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        Log.d("LiveDataCallAdapter",response.body().toString());
                        T body = response.body();
                        postValue(body);
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        postValue(null);
                    }
                });
            }
        }
    }
}
