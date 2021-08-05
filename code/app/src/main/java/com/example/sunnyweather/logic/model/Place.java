package com.example.sunnyweather.logic.model;

import android.content.Context;

public class Place {
    private Location location;
    private String name;
    private String formatted_address;

    public Place(Location location, String name, String formatted_address) {
        this.location = location;
        this.name = name;
        this.formatted_address = formatted_address;
    }

    public Place(Context context) {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
