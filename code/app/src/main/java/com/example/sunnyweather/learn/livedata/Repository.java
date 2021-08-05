package com.example.sunnyweather.learn.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Repository {
    public static final Repository repository = new Repository();
    private Repository() {
    }
    public LiveData<User> getUserById(String userId){
        MutableLiveData<User> liveData = new MutableLiveData<User>();
        liveData.setValue(new User(userId,userId,0));
        return liveData;
    }
}
