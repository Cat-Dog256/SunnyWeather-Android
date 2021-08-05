package com.example.sunnyweather.ui.place;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunnyweather.R;
import com.example.sunnyweather.WeatherMainActivity;
import com.example.sunnyweather.logic.model.Place;
import com.example.sunnyweather.logic.model.PlaceResponse;
import com.example.sunnyweather.ui.weather.WeatherActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceFragment extends Fragment {
    private PlaceViewModel viewModel;
    private RecyclerView recyclerView;
    private EditText searchPlaceEdit;
    private ImageView bgImageView;
    private PlaceAdapter adapter;

    public PlaceViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(PlaceViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        Log.d("PlaceViewModel-61",getActivity().toString());
        Log.d("PlaceViewModel-62",WeatherMainActivity.class.toString());

        if ((getActivity() instanceof  WeatherMainActivity) && viewModel.isPlaceSaved()){
            Place place = viewModel.getSavePlace();
            Intent intent = new Intent(getContext(), WeatherActivity.class);
            intent.putExtra("location_lng",place.getLocation().getLng());
            intent.putExtra("location_lat",place.getLocation().getLat());
            intent.putExtra("place_name",place.getName());
            startActivity(intent);
            getActivity().finish();
            return;
        }
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        bgImageView = getActivity().findViewById(R.id.bgImageView);
        searchPlaceEdit = getActivity().findViewById(R.id.search_place_text);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceAdapter(this,viewModel.placeArrayList);
        recyclerView.setAdapter(adapter);

        /*监听输入框*/
        searchPlaceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (content.isEmpty() || content == null){
                    recyclerView.setVisibility(View.GONE);
                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.placeArrayList.clear();
                    adapter.notifyDataSetChanged();
                }else {
                    //发起请求
                    viewModel.searchPlaces(content);
                }
            }
        });

        viewModel.placeLiveData.observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                if (places != null){
                    recyclerView.setVisibility(View.VISIBLE);
                    bgImageView.setVisibility(View.GONE);
                    viewModel.placeArrayList.clear();
                    viewModel.placeArrayList.addAll(places);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),"未查询到任何地点",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
