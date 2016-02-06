package com.surefor.weather.api;

import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.forecast.WeatherForecast;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class OpenWeatherMapClient {
    private static OpenWeatherMapClient instance = null ;
    private OpenWeatherMapService service = null ;

    public static OpenWeatherMapClient getInstance() {
        if(instance == null) {
            instance = new OpenWeatherMapClient() ;
        }
        return instance ;
    }

    private OpenWeatherMapClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(SuerForWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        service = retrofit.create(OpenWeatherMapService.class) ;
    }

    public Observable<WeatherCurrent> getCurrent(Long id) {
        return service.getCurrent(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
    }

    public Observable<WeatherCurrent> getCurrent(String name) {
        return service.getCurrent(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherForecast>  getForecast(Long id) {
        return service.getForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherForecast> getForecast(String name) {
        return service.getForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherDailyForecast> getDailyForecast(Long id) {
        return service.getDailyForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherDailyForecast> getDailyForecast(String name) {
        return service.getDailyForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

/*  comment out synchronous request
    public CurrentWeather getCurrentWeather(Long id) {
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(SuerForWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build() ;

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class) ;

        CurrentWeather currentWeather = null ;

        try
        {
            Call<CurrentWeather> call = service.getCurrentWeather(id,
                    String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
            Response<CurrentWeather> response = call.execute() ;
            currentWeather = response.body() ;
        }
        catch (Exception e) {
            Log.e("getCurrentWeather", e.getMessage(), e) ;
        }

        return  currentWeather ;
    }*/


}
