package com.example.sunnyweather.ui.weather;

import com.example.sunnyweather.logic.Repository;
import com.example.sunnyweather.logic.model.Location;
import com.example.sunnyweather.logic.model.Weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Location> locationMutableLiveData = new MutableLiveData<>();
    private String locationLng;
    private String locationLat;
    private String placeName;

    public LiveData<Weather> weatherLiveData = Transformations.switchMap(locationMutableLiveData,location -> {
        return Repository.refreshWeather(location.getLng(),location.getLat());
    });

    public void refreshWeather(String lng, String lat){
        locationMutableLiveData.setValue(new Location(lng,lat));
    }
    public String getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
