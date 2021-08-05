package com.example.sunnyweather.logic;

import android.util.Log;

import com.example.sunnyweather.logic.dao.PlaceDao;
import com.example.sunnyweather.logic.model.DailyResponse;
import com.example.sunnyweather.logic.model.Place;
import com.example.sunnyweather.logic.model.PlaceResponse;
import com.example.sunnyweather.logic.model.RealtimeResponse;
import com.example.sunnyweather.logic.model.Weather;
import com.example.sunnyweather.logic.network.ApiService;
import com.example.sunnyweather.logic.network.PlaceService;
import com.example.sunnyweather.logic.network.WeatherService;

import java.nio.file.WatchEvent;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    public static LiveData<List<Place>> searchPlace(String query){
        MutableLiveData<List<Place>> responseLiveData = new MutableLiveData<>();
        Call<PlaceResponse> call = ApiService.create(PlaceService.class).searchPlaces(query);
        call.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                Log.d("PlaceViewModel",response.toString());
                PlaceResponse placeResponse = response.body();
                if ("ok".equals(placeResponse.getStatus())){
                    responseLiveData.postValue(placeResponse.getPlaces());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {

            }
        });
        return responseLiveData;
    }
    public static void savePlace(Place place){
        PlaceDao.getInstance().savePlace(place);
    }
    public static Place getSavePlace(){
        return PlaceDao.getInstance().getSavePlace();
    }
    public static Boolean isPlaceSaved(){
        return PlaceDao.getInstance().isPlaceSaved();
    }
    public static LiveData<Weather> refreshWeather(String lng, String lat){
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Call<RealtimeResponse> realtimeResponseCall = ApiService.create(WeatherService.class).getRealtimeWeather(lng,lat);
        Call<DailyResponse> dailyResponseCall = ApiService.create(WeatherService.class).getDailyWeather(lng,lat);
        MutableLiveData<Weather> weatherMutableLiveData = new MutableLiveData<>();
        Weather weather = new Weather(null,null);
        realtimeResponseCall.enqueue(new Callback<RealtimeResponse>() {
            @Override
            public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                Log.d("Repository",response.toString());

                Log.d("Repository",response.body().toString());
                if ("ok".equals(response.body().getStatus()) ){
                    weather.setRealtime(response.body().getResult().getRealtime());
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                countDownLatch.countDown();
            }
        });
        dailyResponseCall.enqueue(new Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                Log.d("Repository",response.toString());

                Log.d("Repository",response.body().toString());
                if ("ok".equals(response.body().getStatus()) ){
                    weather.setDaily(response.body().getResult().getDaily());
                }
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
                countDownLatch.countDown();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    weatherMutableLiveData.postValue(weather);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return weatherMutableLiveData;
    }


}
