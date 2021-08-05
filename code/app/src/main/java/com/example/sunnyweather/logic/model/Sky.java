package com.example.sunnyweather.logic.model;

import com.example.sunnyweather.R;

import java.util.HashMap;
import java.util.Map;

public class Sky {
    private String info;
    private int icon;
    private int bg;

    public Sky(String info, int icon, int bg) {
        this.info = info;
        this.icon = icon;
        this.bg = bg;
    }
    public static Sky getSky(String skycon){
        Sky sky =  new Sky("晴", R.drawable.ic_clear_day,R.drawable.bg_clear_day);
        if ("CLEAR_DAY".equals(skycon)){

        }else if ("CLEAR_NIGHT".equals(skycon)){
            sky.setIcon(R.drawable.ic_clear_night);
            sky.setBg(R.drawable.bg_clear_night);
        }else if ("PARTLY_CLOUDY_DAY".equals(skycon)){
            sky.setInfo("多云");
            sky.setIcon(R.drawable.ic_partly_cloud_day);
            sky.setBg(R.drawable.bg_partly_cloudy_day);
        }else if ("PARTLY_CLOUDY_NIGHT".equals(skycon)){
            sky.setInfo("多云");
            sky.setIcon(R.drawable.ic_partly_cloud_night);
            sky.setBg(R.drawable.bg_partly_cloudy_night);
        }else if ("CLOUDY".equals(skycon)){
            sky.setInfo("阴");
            sky.setIcon(R.drawable.ic_cloudy);
            sky.setBg(R.drawable.bg_cloudy);
        }else if ("WIND".equals(skycon)){
            sky.setInfo("大风");
            sky.setIcon(R.drawable.ic_cloudy);
            sky.setBg(R.drawable.bg_wind);
        }else if ("LIGHT_RAIN".equals(skycon)){
            sky.setInfo("小雨");
            sky.setIcon(R.drawable.ic_light_rain);
            sky.setBg(R.drawable.bg_rain);
        }else if ("MODERATE_RAIN".equals(skycon)){
            sky.setInfo("中雨");
            sky.setIcon(R.drawable.ic_moderate_rain);
            sky.setBg(R.drawable.bg_rain);
        }else if ("HEAVY_RAIN".equals(skycon)){
            sky.setInfo("大雨");
            sky.setIcon(R.drawable.ic_heavy_rain);
            sky.setBg(R.drawable.bg_rain);
        }else if ("STORM_RAIN".equals(skycon)){
            sky.setInfo("暴雨");
            sky.setIcon(R.drawable.ic_storm_rain);
            sky.setBg(R.drawable.bg_rain);
        }else if ("THUNDER_RAIN".equals(skycon)){
            sky.setInfo("雷阵雨");
            sky.setIcon(R.drawable.ic_thunder_shower);
            sky.setBg(R.drawable.bg_rain);
        }else if ("SLEET".equals(skycon)){
            sky.setInfo("雨夹雪");
            sky.setIcon(R.drawable.ic_sleet);
            sky.setBg(R.drawable.bg_rain);
        }else if ("LIGHT_SNOW".equals(skycon)){
            sky.setInfo("小雪");
            sky.setIcon(R.drawable.ic_light_snow);
            sky.setBg(R.drawable.bg_snow);
        }else if ("MODERATE_SNOW".equals(skycon)){
            sky.setInfo("中雪");
            sky.setIcon(R.drawable.ic_moderate_snow);
            sky.setBg(R.drawable.bg_snow);
        }else if ("HEAVY_SNOW".equals(skycon)){
            sky.setInfo("大雪");
            sky.setIcon(R.drawable.ic_heavy_snow);
            sky.setBg(R.drawable.bg_snow);
        }else if ("STORM_SNOW".equals(skycon)){
            sky.setInfo("暴雪");
            sky.setIcon(R.drawable.ic_heavy_snow);
            sky.setBg(R.drawable.bg_snow);
        }else if ("HAIL".equals(skycon)){
            sky.setInfo("冰雹");
            sky.setIcon(R.drawable.ic_hail);
            sky.setBg(R.drawable.bg_snow);
        }else if ("LIGHT_HAZE".equals(skycon)){
            sky.setInfo("轻度雾霾");
            sky.setIcon(R.drawable.ic_light_haze);
            sky.setBg(R.drawable.bg_fog);
        }else if ("MODERATE_HAZE".equals(skycon)){
            sky.setInfo("中度雾霾");
            sky.setIcon(R.drawable.ic_moderate_haze);
            sky.setBg(R.drawable.bg_fog);
        }else if ("HEAVY_HAZE".equals(skycon)){
            sky.setInfo("重度雾霾");
            sky.setIcon(R.drawable.ic_heavy_haze);
            sky.setBg(R.drawable.bg_fog);
        }else if ("FOG".equals(skycon)){
            sky.setInfo("雾");
            sky.setIcon(R.drawable.ic_fog);
            sky.setBg(R.drawable.bg_fog);
        }else if ("DUST".equals(skycon)){
            sky.setInfo("浮尘");
            sky.setIcon(R.drawable.ic_fog);
            sky.setBg(R.drawable.bg_fog);
        }
        return sky;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }
}
