package com.surefor.weather.api;

import com.surefor.weather.entity.geocode.GeoCode;
import com.surefor.weather.entity.place.AutocompletePlace;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Ethan on 2016-02-01.
 */
public interface GoogleMapService {
    // https://maps.googleapis.com/maps/api/geocode/json?address=oakville,ca&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    @GET("/maps/api/geocode/json")
    Observable<GeoCode> getGeoCode(@Query("address") String address, @Query("key") String appid) ;

	// https://maps.googleapis.com/maps/api/place/autocomplete/json?input=oa&types=(cities)&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=oa&types=geocode&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    @GET("/maps/api/place/autocomplete/json")
    Observable<AutocompletePlace> getAutocompletePlace(@Query("input") String input, @Query("types") String types, @Query("key") String appid) ;

}
