package com.surefor.weather.entity.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ethan on 05/11/2015.
 */
public class City {
    @SerializedName("_id")
    @Expose
    Long id ;


    @SerializedName("name")
    @Expose
    String name ;

    @SerializedName("coord")
    @Expose
    Coordinate coord ;

    @SerializedName("country")
    @Expose
    String country ;

    public City() {

    }

    public City(Long id, String name, String country, Coordinate coord) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coord = coord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public double getLongitude() {
        return this.coord.getLongitude() ;
    }

    public void setLongitude(Double longitude) {
        coord.setLongitude(longitude) ;
    }

    public double getLatitude() {
        return this.coord.getLatitude() ;
    }

    public void setLatitude(Double latitude) {
        coord.setLatitude(latitude) ;
    }

    public String getKey() {
        return name + ", " + country ;
    }
}
