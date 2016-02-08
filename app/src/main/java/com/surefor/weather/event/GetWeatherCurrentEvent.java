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

        public Request(long id) {
            super(id);
        }

        public Request(String name) {
            super(name);
        }
    }

    public static class Response extends GetWeatherEvent.Response<WeatherCurrent>  {
        public Response(WeatherCurrent weatherCurrent) {
            super(weatherCurrent);
        }
    }
}
