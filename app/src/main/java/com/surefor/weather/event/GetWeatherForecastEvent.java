package com.surefor.weather.event;

import com.surefor.weather.entity.forecast.WeatherForecast;

/**
 * Created by Ethan on 2016-02-04.
 */
public class GetWeatherForecastEvent extends GetWeatherEvent<WeatherForecast> {

    public GetWeatherForecastEvent() {

    }

    public static class Request extends GetWeatherEvent.Request  {

        public Request(long id) {
            super(id);
        }

        public Request(String name) {
            super(name);
        }
    }

    public static class Response extends GetWeatherEvent.Response<WeatherForecast>  {
        public Response(WeatherForecast weatherForecast) {
            super(weatherForecast);
        }
    }

}
