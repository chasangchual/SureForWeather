package com.surefor.weather.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ethan on 05/11/2015.
 */
public class Coordinate {
    @SerializedName("lon")
    @Expose
    Double longitude ;

    @SerializedName("lat")
    @Expose
    Double latitude ;

    public Coordinate() {

    }

    public Coordinate(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
