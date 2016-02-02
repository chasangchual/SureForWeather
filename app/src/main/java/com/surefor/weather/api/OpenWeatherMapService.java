package com.surefor.weather.api ;

import com.surefor.weather.entity.weather.CurrentWeather;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface OpenWeatherMapService {
    // current weather data for one location
    @GET ("/data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("id") Long id, @Query("appid") String appid) ;

    // http://api.openweathermap.org/data/2.5/weather?q=Oakville,%20ON,%20Canada&appid=3cd3c05dc4ff13ba9967374dfd7ed852
    @GET ("/data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String name, @Query("appid") String appid) ;

    // 5 day / 3 hour forecast data
    @GET ("/data/2.5/forecast")
    Call<CurrentWeather> getForecast(@Query("id") Long id, @Query("appid") String appid) ;

    @GET ("/data/2.5/forecast")
    Call<CurrentWeather> getForecast(@Query("q") String name, @Query("appid") String appid) ;

    // 16 days / daily forecast data
    @GET ("/data/2.5/forecast/daily")
    Call<CurrentWeather> getDailyForecast(@Query("id") Long id, @Query("appid") String appid) ;

    @GET ("/data/2.5/forecast/daily")
    Call<CurrentWeather> getDailyForecast(@Query("q") String name, @Query("appid") String appid) ;
}
