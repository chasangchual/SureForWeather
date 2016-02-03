package com.surefor.weather.api;

import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.weather.CurrentWeather;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
                .baseUrl(SuerForWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        service = retrofit.create(OpenWeatherMapService.class) ;
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

    public Call<CurrentWeather> getCurrentWeather(Long id) {
        return service.getCurrentWeather(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
    }

    public Call<CurrentWeather> getCurrentWeather(String name) {
        return service.getCurrentWeather(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map)));
    }

    public void getForecast(Long id) {
        Call<CurrentWeather> weather = service.getForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getForecast(String name) {
        Call<CurrentWeather> weather = service.getForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getDailyForecast(Long id) {
        Call<CurrentWeather> weather = service.getDailyForecast(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getDailyForecast(String name) {
        Call<CurrentWeather> weather = service.getDailyForecast(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
