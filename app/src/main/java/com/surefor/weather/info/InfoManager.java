package com.surefor.weather.info;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ethan on 2016-02-05.
 */
public class InfoManager {
    private Map<String, Info> weathers = new HashMap<>() ;
    private static InfoManager instance = null ;

    public static InfoManager getInstance() {
        if(instance == null) {
            instance = new InfoManager() ;
        }
        return instance ;
    }

    private InfoManager() {
    }

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Log.d("InfoManager.toJson()", gson.toJson(weathers)) ;
        // System.out.println(gson.toJson(weathers)) ;
        return gson.toJson(weathers) ;
    }

    public void setWeatherCurrent(String cityName, WeatherCurrent current) {
        ensure(cityName) ;
        weathers.get(cityName).setCurrent(current);
    }

    public void setWeatherForecast(String cityName, WeatherForecast forecast) {
        ensure(cityName) ;
        weathers.get(cityName).setForecast(forecast);
    }

    public void setWeatherDailyForecast(String cityName, WeatherDailyForecast dailyForecast) {
        ensure(cityName) ;
        weathers.get(cityName).setDailyForecast(dailyForecast);
    }

    private void ensure(String cityName) {
        if(!weathers.containsKey(cityName)) {
            weathers.put(cityName, new Info(cityName)) ;
        }
    }

    public class Info {
        private String name ;
        private WeatherCurrent current = null ;
        private WeatherForecast forecast = null ;
        private WeatherDailyForecast dailyForecast = null ;

        public Info(String name) {
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
        }
    }
}
