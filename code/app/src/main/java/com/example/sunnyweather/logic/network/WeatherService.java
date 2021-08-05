package com.example.sunnyweather.logic.network;

import com.example.sunnyweather.logic.model.DailyResponse;
import com.example.sunnyweather.logic.model.RealtimeResponse;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("v2.5/kr5grQRkONQl2ZUj/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather(@Path("lng") String lng,
                                              @Path("lat") String lat);
    @GET("v2.5/kr5grQRkONQl2ZUj/{lng},{lat}/daily.json")
    Call<DailyResponse>  getDailyWeather(@Path("lng") String lng,
                                             @Path("lat") String lat);;
}
