package com.surefor.weather.event;

import com.surefor.weather.entity.current.WeatherCurrent;

/**
 * Created by Ethan on 2016-02-03.
 */
public class GetWeatherCurrentEvent extends GetWeatherEvent<WeatherCurrent> {
    public GetWeatherCurrentEvent() {
        super() ;
    }

    public static class Request extends GetWeatherEvent.Request  {
        public Request(String name) {
            super(name);
        }
    }

    public static class Response extends GetWeatherEvent.Response<WeatherCurrent>  {
        public Response(WeatherCurrent weatherCurrent) {
            super(getCityName(weatherCurrent), weatherCurrent);
        }

        public Response(String name, WeatherCurrent weatherCurrent) {
            super(name, weatherCurrent);
        }
    }

    private static String getCityName(WeatherCurrent weatherCurrent) {
        return weatherCurrent.getName() + "," + weatherCurrent.getSys().getCountry() ;
    }
}
