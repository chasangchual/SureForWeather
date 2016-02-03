package com.surefor.weather.api;

import com.squareup.otto.Bus;
import com.surefor.weather.entity.weather.CurrentWeather;
import com.surefor.weather.event.GetCurrentWeatherEvent;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ethan on 2016-02-03.
 */
public class OpenWeatherMap {
    private Bus bus = null ;
    Call<CurrentWeather> call = null ;

    public OpenWeatherMap(Bus bus) {
        this.bus = bus ;
    }

    public void handleGetCurrentWeather(GetCurrentWeatherEvent.Request request) {
        OpenWeatherMapClient client = OpenWeatherMapClient.getInstance() ;

        Callback<CurrentWeather> callback = new Callback<CurrentWeather>() {

            @Override
            public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    GetCurrentWeatherEvent event = new GetCurrentWeatherEvent(response.body()) ;
                    // trigger event handler to update UI to show current weather information
                    bus.post(event.getResponse());
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        } ;

        if(request.getRequestType() == GetCurrentWeatherEvent.REQUEST_TYPE.BY_ID) {
            call = client.getCurrentWeather(request.getId());
        } else if (request.getRequestType() == GetCurrentWeatherEvent.REQUEST_TYPE.BY_NAME) {
            call = client.getCurrentWeather(request.getName());
        }

        call.enqueue(callback);
    }
}
