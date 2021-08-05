package com.example.sunnyweather;

import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {
    public static final String TOKEN = "kr5grQRkONQl2ZUj";
    private static Context context;
    public static Context getContent(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
