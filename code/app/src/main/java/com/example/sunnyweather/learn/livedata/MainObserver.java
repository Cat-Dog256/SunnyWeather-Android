package com.example.sunnyweather.learn.livedata;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void activityStart(){
        Log.d("MainObserver","activityStart");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private  void activityStop(){
        Log.d("MainObserver","activityStop");
    }
}
