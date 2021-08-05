package com.example.sunnyweather.learn.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private Integer countReserved;
    private MutableLiveData<Integer> counter = new MutableLiveData<Integer>();

    private MutableLiveData<String> userIdMutableLiveData = new MutableLiveData<String>();


    public LiveData<User> userLiveData = Transformations.switchMap(userIdMutableLiveData,useId -> {
        return Repository.repository.getUserById(useId);
    });
    public MainViewModel(Integer countReserved) {
        this.countReserved = countReserved;
        counter.setValue(countReserved);
    }
    public void plusOne(){
        Integer count = counter.getValue();
        counter.setValue(count+1);
    }
    public void clear(){
        counter.setValue(0);
    }

    public MutableLiveData<Integer> getCounter() {
        return counter;
    }


    public void getUserById(String userId){
        userIdMutableLiveData.setValue(userId);
    }
    //    private Integer counter = 0;
//
//    public MainViewModel(Integer counter) {
//        this.counter = counter;
//    }
//
//    public Integer getCounter() {
//        return counter;
//    }
//
//    public void setCounter(Integer counter) {
//        this.counter = counter;
//    }
}
