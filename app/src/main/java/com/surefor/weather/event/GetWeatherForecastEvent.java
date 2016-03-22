package com.surefor.weather.event;

import com.surefor.weather.entity.forecast.WeatherForecast;

/**
 * Created by Ethan on 2016-02-04.
 */
public class GetWeatherForecastEvent extends GetWeatherEvent<WeatherForecast> {

    public GetWeatherForecastEvent() {

    }

    public static class Request extends GetWeatherEvent.Request  {
        public Request(String name) {
            super(name);
        }
    }

    public static class Response extends GetWeatherEvent.Response<WeatherForecast>  {
        public Response(WeatherForecast weatherForecast) {
            super(getCityName(weatherForecast), weatherForecast);
        }

        public Response(String name, WeatherForecast weatherForecast) {
            super(name, weatherForecast);
        }
    }

    private static String getCityName(WeatherForecast weatherForecast) {
        return weatherForecast.getCity().getName() + "," + weatherForecast.getCity().getName() ;
    }
}
