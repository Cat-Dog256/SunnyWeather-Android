package com.example.sunnyweather.logic.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.lifecycle.LiveData;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @javax.annotation.Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class){
            return null;
        }
        Type observableType = getParameterUpperBound(0,(ParameterizedType)returnType);
        Type rawType = getRawType(observableType);
        boolean isApiResponse = true;
        if (rawType != ApiResponse.class){
            isApiResponse = false;
        }
        if (observableType instanceof ParameterizedType){
            throw new IllegalArgumentException("resource must be parameterized");
        }

        return new LiveDataCallAdapter<>(observableType,isApiResponse);
    }
}
