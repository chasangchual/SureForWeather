package com.surefor.weather.info;

import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

import java.util.HashMap;
import java.util.Map;

/**
 * collection of retrieved weather information.
 *
 * Created by Ethan on 2016-02-05.
 */
public class CityWeatherInfoCollection {
    private Map<String, CityWeatherInfo> weathers = null ;
    private static CityWeatherInfoCollection instance ;

    // singleton implementation
    public static CityWeatherInfoCollection getInstance() {
        if(instance == null) {
            instance = new CityWeatherInfoCollection() ;
        }
        return instance ;
    }

    /**
     * private constructor supporting singleton
     */
    private CityWeatherInfoCollection() {
        weathers = new HashMap<>() ;
    }

    /**
     * clears weather information map
     */
    public void clear() {
        weathers.clear() ;
    }

    /**
     * returns count of weather information map
     */
    public Integer count() {
        return weathers.size() ;
    }



    public Map<String, CityWeatherInfo> getWeathers() {
        return new HashMap<String, CityWeatherInfo>(weathers) ;
    }

    /**
     * returns whether weather information map contains specified city name.
     */
    public Boolean contains(String cityName) {
        return weathers.containsKey(cityName) ;
    }

    /**
     * set current weather data
     */
    public void setWeatherCurrent(String cityName, WeatherCurrent current) {
        ensure(cityName) ;
        weathers.get(cityName).setCurrent(current);
    }

    /**
     * set weather forecast data
     */
    public void setWeatherForecast(String cityName, WeatherForecast forecast) {
        ensure(cityName) ;
        weathers.get(cityName).setForecast(forecast);
    }

    /**
     * set weather daily forecast data
     */
    public void setWeatherDailyForecast(String cityName, WeatherDailyForecast dailyForecast) {
        ensure(cityName) ;
        weathers.get(cityName).setDailyForecast(dailyForecast);
    }

    /**
     * if the specified city name does not exist in the map, it creates an entity.
     * do nothing otherwise.
     */
    private void ensure(String cityName) {
        if(!weathers.containsKey(cityName)) {
            weathers.put(cityName, new CityWeatherInfo(cityName)) ;
        }
    }
}
