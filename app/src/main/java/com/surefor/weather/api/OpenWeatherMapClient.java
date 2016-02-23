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
            instance = new OpenWeatherMapClient(SuerForWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString()) ;
        }
        return instance ;
    }

    public static OpenWeatherMapClient getInstance(String baseURL) {
        if(instance == null) {
            instance = new OpenWeatherMapClient(baseURL) ;
        }
        return instance ;
    }

    private OpenWeatherMapClient(String baseURL) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        service = retrofit.create(OpenWeatherMapService.class) ;
    }

    public Observable<WeatherCurrent> getCurrent(Long id) {
        return getCurrent(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
    }

    public Observable<WeatherCurrent> getCurrent(String name) {
        return getCurrent(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherCurrent> getCurrent(Long id, String key) {
        return service.getCurrent(id, key) ;
    }

    public Observable<WeatherCurrent> getCurrent(String name, String key) {
        return service.getCurrent(name, key);
    }

    public Observable<WeatherForecast>  getForecast(Long id) {
        return getForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherForecast> getForecast(String name) {
        return getForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherForecast>  getForecast(Long id, String key) {
        return service.getForecast(id, key);
    }

    public Observable<WeatherForecast> getForecast(String name, String key) {
        return service.getForecast(name, key);
    }

    public Observable<WeatherDailyForecast> getDailyForecast(Long id) {
        return getDailyForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherDailyForecast> getDailyForecast(String name) {
        return getDailyForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public Observable<WeatherDailyForecast> getDailyForecast(Long id, String key) {
        return service.getDailyForecast(id, key);
    }

    public Observable<WeatherDailyForecast> getDailyForecast(String name, String key) {
        return service.getDailyForecast(name, key);
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
