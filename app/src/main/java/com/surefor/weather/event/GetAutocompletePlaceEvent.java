package com.surefor.weather.event;

import com.surefor.weather.api.GoogleMapService;
import com.surefor.weather.entity.place.Prediction;

import java.util.List;

/**
 * Created by Ethan on 2016-02-02.
 */
public class GetAutocompletePlaceEvent {
    public GetAutocompletePlaceEvent() {
    }

    public Request getRequest(String place) {
        return new Request(place) ;
    }

    public Response getResponse(List<Prediction> predictions) {
        return new Response(predictions) ;
    }

    public static class Request {
        private final String place;

        public Request(String palce) {
            this.place = palce ;
        }

        public String getPlace() {
            return place;
        }
    }

    public static class Response {
        private final List<Prediction> predictions ;
        private final String status ;

        public Response(List<Prediction> predictions) {
            this.predictions = predictions ;
            this.status = GoogleMapService.STATUS_OK ;
        }

        public Response(List<Prediction> predictions, String status) {
            this.predictions = predictions ;
            this.status = status ;
        }

        public List<Prediction> getPredictions() {
            return predictions;
        }
        public String getStatus() {
            return status ;
        }
        public Integer size() {
            return predictions.size() ;
        }
    }
}
