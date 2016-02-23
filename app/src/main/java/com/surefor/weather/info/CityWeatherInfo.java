package com.surefor.weather.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

/**
 * represents weather information of current, forecast and daily forecast
 *
 * Created by Ethan on 2016-02-17.
 */
public class CityWeatherInfo {
    @SerializedName("name")
    @Expose
    private String name ;
    @SerializedName("current")
    @Expose
    private WeatherCurrent current = null ;
    @SerializedName("forecast")
    @Expose
    private WeatherForecast forecast = null ;
    @SerializedName("dailyForecast")
    @Expose
    private WeatherDailyForecast dailyForecast = null ;

    public CityWeatherInfo(String name) {
        this.name = name ;
    }

    public WeatherCurrent getCurrent() {
        return current;
    }

    public void setCurrent(WeatherCurrent current) {
        this.current = current;
    }

    public WeatherForecast getForecast() {
        return forecast;
    }

    public void setForecast(WeatherForecast forecast) {
        this.forecast = forecast;
    }

    public WeatherDailyForecast getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(WeatherDailyForecast dailyForecast) {
        this.dailyForecast = dailyForecast;
    }}
