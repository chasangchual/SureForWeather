package com.surefor.weather.db;

/**
 * Created by ethan on 22/11/2015.
 */
public class CityEntity {
    private String name ;
    private String country ;
    private Double longitude ;

    public CityEntity() {

    }

    public CityEntity(String name, String country, Double longitude, Double latitude) {
        this.name = name;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    private Double latitude ;
}
