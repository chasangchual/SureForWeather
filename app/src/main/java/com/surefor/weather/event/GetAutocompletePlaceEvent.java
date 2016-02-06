package com.surefor.weather.event;

import java.util.List;

/**
 * Created by Ethan on 2016-02-02.
 */
public class GetAutocompletePlaceEvent {
    private Request request = new Request() ;
    private Response response = new Response() ;

    public GetAutocompletePlaceEvent() {
    }

    public Request getRequest(String place) {
        request.setPlace(place);
        return this.request ;
    }

    public Response getResponse(List<String> places) {
        response.setPlaces(places);
        return this.response ;
    }

    public class Request {
        private String place;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }
    }

    public class Response {
        private List<String> places ;

        public Response() {
        }

        public List<String> getPlaces() {
            return places;
        }

        public void setPlaces(List<String> places) {
            places.clear();
            this.places.addAll(places);
        }

        public void addPlace(String place) {
            this.places.add(place);
        }

        public Integer size() {
            return this.places.size() ;
        }
    }
}
