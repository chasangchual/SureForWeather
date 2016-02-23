package com.surefor.weather.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Ethan on 2016-02-15.
 */
public class OpenWeatherMapTest {
    private final String URL = "http://api.openweathermap.org" ;
    private final String KEY = "3cd3c05dc4ff13ba9967374dfd7ed852" ;
    private final String CITY = "Oakville, ON, Canada" ;


    @Test
    public void testGetCurrentWeather() {
        final GsonBuilder gsonBuilder = new GsonBuilder() ;
        final Gson gson = gsonBuilder.create() ;

        OpenWeatherMapClient openWeatherMapClient = OpenWeatherMapClient.getInstance(URL) ;
        Observable<WeatherCurrent> observable = openWeatherMapClient.getCurrent(CITY, KEY) ;

        observable.subscribe(new Action1<WeatherCurrent>() {
            @Override
            public void call(WeatherCurrent weatherCurrent) {
                String out = gson.toJson(weatherCurrent) ;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("testGetCurrentWeather", throwable.getMessage());
            }
        });
    }

    @Test
    public void testGetForecast() {
        final GsonBuilder gsonBuilder = new GsonBuilder() ;
        final Gson gson = gsonBuilder.create() ;

        OpenWeatherMapClient openWeatherMapClient = OpenWeatherMapClient.getInstance(URL) ;
        Observable<WeatherForecast> observable = openWeatherMapClient.getForecast(CITY, KEY) ;

        observable.subscribe(new Action1<WeatherForecast>() {
            @Override
            public void call(WeatherForecast weatherForecast) {
                String out = gson.toJson(weatherForecast) ;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("testGetForecast", throwable.getMessage());
            }
        });
    }

    @Test
    public void testDailyForecast() {
        final GsonBuilder gsonBuilder = new GsonBuilder() ;
        final Gson gson = gsonBuilder.create() ;

        OpenWeatherMapClient openWeatherMapClient = OpenWeatherMapClient.getInstance(URL) ;
        Observable<WeatherDailyForecast> observable = openWeatherMapClient.getDailyForecast(CITY, KEY) ;

        observable.subscribe(new Action1<WeatherDailyForecast>() {
            @Override
            public void call(WeatherDailyForecast dailyForecast) {
                String out = gson.toJson(dailyForecast) ;
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("testDailyForecast", throwable.getMessage());
            }
        });
    }

}
