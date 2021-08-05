package com.example.sunnyweather.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sunnyweather.SunnyWeatherApplication;
import com.example.sunnyweather.logic.model.Place;
import com.google.gson.Gson;

public class PlaceDao {
    private Context mContext;
    private static PlaceDao sInstance;
    private SharedPreferences preferences;
    private PlaceDao() {
        this.mContext = SunnyWeatherApplication.getContent();
        if (mContext == null){
            Log.d("PlaceDao","mContext is null");
        }

        preferences = mContext.getSharedPreferences("place_sp",Context.MODE_PRIVATE);
    }
    public static synchronized PlaceDao getInstance(){
        if (sInstance == null){
            sInstance = new PlaceDao();
        }
        return sInstance;
    }
    public void savePlace(Place place){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("place", new Gson().toJson(place));
        editor.commit();
    }

    public Place getSavePlace(){
        String placeJson = preferences.getString("place","");
        return new Gson().fromJson(placeJson,Place.class);
    }

    public Boolean isPlaceSaved(){
        Log.d("PlaceDao",preferences.toString());
        return preferences.contains("place");
    }

}
