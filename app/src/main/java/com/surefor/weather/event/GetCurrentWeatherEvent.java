package com.surefor.weather.event;

import com.surefor.weather.entity.weather.CurrentWeather;

/**
 * Created by Ethan on 2016-02-03.
 */
public class GetCurrentWeatherEvent {
    private Request request = new Request() ;
    private Response response = new Response() ;

    public GetCurrentWeatherEvent(Long id) {
        request.setRequestType(REQUEST_TYPE.BY_ID);
        request.setId(id);
    }

    public GetCurrentWeatherEvent(String name) {
        request.setRequestType(REQUEST_TYPE.BY_NAME);
        request.setName(name);
    }

    public GetCurrentWeatherEvent(CurrentWeather currentWeather) {
        response.setCurrentWeather(currentWeather);
    }

    public Request getRequest() {
        return this.request ;
    }

    public Response getResponse() {
        return this.response ;
    }

    public enum REQUEST_TYPE { BY_ID, BY_NAME }

    public class Request {
        private REQUEST_TYPE requestType ;
        private Long id ;
        private String name ;

        public REQUEST_TYPE getRequestType() {
            return requestType;
        }

        public void setRequestType(REQUEST_TYPE requestType) {
            this.requestType = requestType;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Response {
        private CurrentWeather currentWeather ;

        public Response() {
        }

        public CurrentWeather getCurrentWeather() {
            return currentWeather;
        }

        public void setCurrentWeather(CurrentWeather currentWeather) {
            this.currentWeather = currentWeather;
        }
    }
}
