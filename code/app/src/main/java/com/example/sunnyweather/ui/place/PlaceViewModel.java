package com.example.sunnyweather.ui.place;

import android.util.Log;

import com.example.sunnyweather.logic.Repository;
import com.example.sunnyweather.logic.dao.PlaceDao;
import com.example.sunnyweather.logic.model.Place;
import com.example.sunnyweather.logic.model.PlaceResponse;
import com.example.sunnyweather.logic.network.ApiService;
import com.example.sunnyweather.logic.network.PlaceService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceViewModel extends ViewModel {
    private MutableLiveData<String> searchLiveData = new MutableLiveData<String>();
    public ArrayList<Place> placeArrayList = new ArrayList<Place>();
    //获取数据
    LiveData<List<Place>> placeLiveData = Transformations.switchMap(searchLiveData, query -> {
      return Repository.searchPlace(query);
    });

    public void searchPlaces(String query){
        searchLiveData.setValue(query);
    }


    public void savePlace(Place place){
       Repository.savePlace(place);
    }
    public Place getSavePlace(){
        return Repository.getSavePlace();
    }
    public Boolean isPlaceSaved(){
        return Repository.isPlaceSaved();
    }
}
