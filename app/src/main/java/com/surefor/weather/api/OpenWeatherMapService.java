package com.surefor.weather.api ;

import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.forecast.WeatherForecast;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;


public interface OpenWeatherMapService {
    // current weather data for one location
    @GET ("/data/2.5/weather")
    Observable<WeatherCurrent> getCurrent(@Query("id") Long id, @Query("appid") String appid) ;

    // http://api.openweathermap.org/data/2.5/weather?q=Oakville,%20ON,%20Canada&appid=3cd3c05dc4ff13ba9967374dfd7ed852
    @GET ("/data/2.5/weather")
    Observable<WeatherCurrent> getCurrent(@Query("q") String name, @Query("appid") String appid) ;

    // 5 day / 3 hour forecast data
    @GET ("/data/2.5/forecast")
    Observable<WeatherForecast> getForecast(@Query("id") Long id, @Query("appid") String appid) ;

    // http://api.openweathermap.org/data/2.5/forecast?q=Oakville,%20ON,%20Canada&appid=3cd3c05dc4ff13ba9967374dfd7ed852
    @GET ("/data/2.5/forecast")
    Observable<WeatherForecast> getForecast(@Query("q") String name, @Query("appid") String appid) ;

    // 16 days / daily forecast data
    @GET ("/data/2.5/forecast/daily")
    Observable<WeatherDailyForecast> getDailyForecast(@Query("id") Long id, @Query("appid") String appid) ;

    // http://api.openweathermap.org/data/2.5/forecast/daily?q=Oakville,%20ON,%20Canada&appid=3cd3c05dc4ff13ba9967374dfd7ed852
    @GET ("/data/2.5/forecast/daily")
    Observable<WeatherDailyForecast> getDailyForecast(@Query("q") String name, @Query("appid") String appid) ;
}
