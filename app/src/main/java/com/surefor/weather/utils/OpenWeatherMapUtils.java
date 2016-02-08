package com.surefor.weather.utils;

import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

/**
 * Created by Ethan on 2016-02-08.
 */
public class OpenWeatherMapUtils {
    public static String getCityName(WeatherCurrent weatherCurrent) {
        return weatherCurrent.getSys().getCountry() + "," + weatherCurrent.getName() ;
    }

    public static String getCityName(WeatherForecast weatherForecast) {
        return weatherForecast.getCity().getCountry() + "," + weatherForecast.getCity().getName() ;
    }

    public static String getCityName(WeatherDailyForecast weatherDailyForecast) {
        return weatherDailyForecast.getCity().getCountry() + "," + weatherDailyForecast.getCity().getName() ;
    }
}
