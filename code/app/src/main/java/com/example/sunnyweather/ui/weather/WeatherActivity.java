package com.example.sunnyweather.ui.weather;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.logic.model.DailyResponse;
import com.example.sunnyweather.logic.model.RealtimeResponse;
import com.example.sunnyweather.logic.model.Sky;
import com.example.sunnyweather.logic.model.Weather;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;
    private SwipeRefreshLayout refreshLayout;
    private ImageButton navButton;
    private DrawerLayout drawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取状态栏
        View decorView = getWindow().getDecorView();
        //改变DecorView()的UI显示
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //设置成透明色
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_weather);
        refreshLayout = findViewById(R.id.swipeRefresh);
        drawerLayout = findViewById(R.id.drawerLayout);
        navButton = findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                InputMethodManager manager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(drawerView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        if (viewModel == null){
            throw new RuntimeException("viewModel is null");
        }

        viewModel.setLocationLng(getIntent().getStringExtra("location_lng"));


        viewModel.setLocationLat(getIntent().getStringExtra("location_lat"));


        viewModel.setPlaceName(getIntent().getStringExtra("place_name"));


        viewModel.weatherLiveData.observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                showWeatherInfo(weather);
                refreshLayout.setRefreshing(false);
            }
        });


        refreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshWeather();
            }
        });

        refreshWeather();
    }
    public void refreshWeather(){
        viewModel.refreshWeather(viewModel.getLocationLng(),viewModel.getLocationLat());
        refreshLayout.setRefreshing(true);
    }
    private void showWeatherInfo(Weather weather){
        TextView placeName = findViewById(R.id.placeName);
        Log.d("showWeatherInfo", String.valueOf(placeName));
        Log.d("showWeatherInfo",viewModel.getPlaceName());

        placeName.setText(viewModel.getPlaceName());
        RealtimeResponse.Realtime realtime = weather.getRealtime();
        DailyResponse.Daily daily = weather.getDaily();
        //填充now.xml
        if (realtime != null){
            TextView currentTemp = findViewById(R.id.currentTemp);
            currentTemp.setText(realtime.getTemperature() + "℃");

            TextView skyInfo = findViewById(R.id.currentSky);
            Sky sky = Sky.getSky(realtime.getSkycon());
            skyInfo.setText(sky.getInfo());
            String currentPM25Text = "空气指数：" + realtime.getAir_quality().getAqi().getChn();
            TextView aqiText = findViewById(R.id.currentAQI);
            aqiText.setText(currentPM25Text);

            View nowLayout = findViewById(R.id.nowLayout);
            nowLayout.setBackgroundResource(sky.getBg());

        }

        //填充forecast.xml
        LinearLayout forecastLayout = findViewById(R.id.forecastLayout);
        forecastLayout.removeAllViews();
        if (daily != null){
            int days = daily.getSkycon().size();
            for (int i = 0; i < days; i++) {
                DailyResponse.Skycon skycon = daily.getSkycon().get(i);
                DailyResponse.Temperature temperature = daily.getTemperature().get(i);

                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);

                TextView dateInfo = view.findViewById(R.id.dateInfo);
                ImageView skyIcon = view.findViewById(R.id.skyIcon);
                TextView skyInfo = view.findViewById(R.id.skyInfo);
                TextView temperatureInfo = view.findViewById(R.id.temperatureInfo);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                dateInfo.setText(simpleDateFormat.format(skycon.getDate()));

                Sky sky = Sky.getSky(skycon.getValue());
                skyIcon.setImageResource(sky.getIcon());

                skyInfo.setText(sky.getInfo());

                String tempText = String.valueOf(temperature.getMin()) + "~" + String.valueOf(temperature.getMax());
                temperatureInfo.setText(tempText);

                forecastLayout.addView(view);
            }

            //填充life_index.xml
            DailyResponse.LifeIndex lifeIndex = daily.getLife_index();

            TextView coldRiskText = findViewById(R.id.coldRiskText);
            TextView dressingText = findViewById(R.id.dressingText);
            TextView ultravioletText = findViewById(R.id.ultravioletText);
            TextView carWashingText = findViewById(R.id.carWashingText);

            coldRiskText.setText(lifeIndex.getColdRisk().get(0).getDesc());
            dressingText.setText(lifeIndex.getDressing().get(0).getDesc());
            ultravioletText.setText(lifeIndex.getUltraviolet().get(0).getDesc());
            carWashingText.setText(lifeIndex.getCarWashing().get(0).getDesc());
        }

        ScrollView scrollView = findViewById(R.id.weatherLayout);
        scrollView.setVisibility(View.VISIBLE);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public WeatherViewModel getViewModel() {
        return viewModel;
    }
}