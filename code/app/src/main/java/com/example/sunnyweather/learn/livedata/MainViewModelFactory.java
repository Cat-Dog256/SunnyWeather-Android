package com.example.sunnyweather.learn.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private Integer counterReserved;

    public MainViewModelFactory(Integer counterReserved) {
        this.counterReserved = counterReserved;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(counterReserved);
    }
}
