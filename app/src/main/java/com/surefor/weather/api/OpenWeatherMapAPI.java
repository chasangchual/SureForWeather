package com.surefor.weather.api;

import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.weather.CurrentWeather;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OpenWeatherMapAPI {
    private static OpenWeatherMapAPI instance = null ;
    private OpenWeatherMapService service = null ;

    public static OpenWeatherMapAPI instance() {
        if(instance == null) {
            instance = new OpenWeatherMapAPI() ;
        }
        return instance ;
    }

    private OpenWeatherMapAPI() {

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

    public void getCurrentWeather(Long id) {
        OpenWeatherMapService service = getOpenWeatherMapService() ;
        Call<CurrentWeather> weather = service.getCurrentWeather(id, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void getCurrentWeather(String name) {
        OpenWeatherMapService service = getOpenWeatherMapService() ;
        Call<CurrentWeather> weather = service.getCurrentWeather(name, String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;

        weather.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getForecast(Long id) {
        OpenWeatherMapService service = getOpenWeatherMapService() ;
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
        OpenWeatherMapService service = getOpenWeatherMapService() ;
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
        OpenWeatherMapService service = getOpenWeatherMapService() ;
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
        OpenWeatherMapService service = getOpenWeatherMapService() ;
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

    private OpenWeatherMapService getOpenWeatherMapService()
    {
        if(service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SuerForWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build() ;

            service = retrofit.create(OpenWeatherMapService.class) ;
        }

        return service ;
    }
}
