package com.surefor.weather.event;

import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;

/**
 * Created by Ethan on 2016-02-04.GetWeatherEvent<WeatherForecast>
 */
public class GetWeatherDailyForecastEvent extends GetWeatherEvent<WeatherDailyForecast> {
    public GetWeatherDailyForecastEvent() {

    }
    public static class Request extends GetWeatherEvent.Request  {
        public Request(String name) {
            super(name);
        }
    }

    public static class Response extends GetWeatherEvent.Response<WeatherDailyForecast>  {
        public Response(WeatherDailyForecast weatherDailyForecast) {
            super(getCityName(weatherDailyForecast), weatherDailyForecast);
        }

        public Response(String name, WeatherDailyForecast weatherDailyForecast) {
            super(name, weatherDailyForecast);
        }
    }

    private static String getCityName(WeatherDailyForecast weatherDailyForecast) {
        return weatherDailyForecast.getCity().getName() + "," + weatherDailyForecast.getCity().getCountry() ;
    }
}
