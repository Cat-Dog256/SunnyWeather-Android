package com.example.sunnyweather.ui.place;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.logic.model.Place;
import com.example.sunnyweather.ui.weather.WeatherActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private PlaceFragment fragment;
    private List<Place> placeList;

    public PlaceAdapter(PlaceFragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView placeName;
        private TextView placeAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.placeName = itemView.findViewById(R.id.placeName);
            this.placeAddress = itemView.findViewById(R.id.placeAddress);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.palce_item,parent,false);
        ViewHolder holder = new  ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Place place = placeList.get(position);
                FragmentActivity activity = fragment.getActivity();
                if (activity instanceof  WeatherActivity){
                    ((WeatherActivity) activity).getDrawerLayout().closeDrawers();
                    ((WeatherActivity) activity).getViewModel().setLocationLng(place.getLocation().getLng());
                    ((WeatherActivity) activity).getViewModel().setLocationLat(place.getLocation().getLat());
                    ((WeatherActivity) activity).getViewModel().setPlaceName(place.getName());
                    ((WeatherActivity) activity).refreshWeather();
                }else {
                    Intent intent = new Intent(parent.getContext(), WeatherActivity.class);
                    intent.putExtra("location_lng",place.getLocation().getLng());
                    intent.putExtra("location_lat",place.getLocation().getLat());
                    intent.putExtra("place_name",place.getName());
                    fragment.startActivity(intent);
                    fragment.getActivity().finish();
                }
                fragment.getViewModel().savePlace(place);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAddress.setText(place.getFormatted_address());
    }



    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
