package com.surefor.weather.info;

import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

/**
 * Created by Ethan on 2016-02-05.
 */
public class InfoManager {
    public static class Info {
        private String name ;
        private Long id ;
        private WeatherCurrent current ;
        private WeatherForecast forecast ;
        private WeatherDailyForecast dailyForecast ;

    }
}
