package com.example.sunnyweather.logic.network;

import com.example.sunnyweather.logic.model.PlaceResponse;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceService {
    @GET("v2/place?token=kr5grQRkONQl2ZUj&lang=zh_CN")
    Call<PlaceResponse> searchPlaces(@Query("query") String  query);

}
