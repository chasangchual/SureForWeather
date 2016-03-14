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
    // indicates that no errors occurred and at least one result was returned.
    public static final String STATUS_OK = "OK" ;

    // indicates that the search was successful but returned no results. This may occur if the search was passed a bounds in a remote location.
    public static final String ZERO_RESULTS = "ZERO_RESULTS" ;

    // indicates that you are over your quota.
    public static final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT" ;

    // indicates that your request was denied, generally because of lack of an invalid key parameter.
    public static final String REQUEST_DENIED = "REQUEST_DENIED" ;

    // generally indicates that the input parameter is missing.
    public static final String INVALID_REQUEST = "INVALID_REQUEST" ;

    // https://maps.googleapis.com/maps/api/geocode/json?address=oakville,ca&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    @GET("/maps/api/geocode/json")
    Observable<GeoCode> getGeoCode(@Query("address") String address, @Query("key") String appid) ;

	// https://maps.googleapis.com/maps/api/place/autocomplete/json?input=oa&types=(cities)&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=oa&types=geocode&key=AIzaSyCk4Y1pUW2O4_2WWboE2rCigFp9bAtPC1k
    @GET("/maps/api/place/autocomplete/json")
    Observable<AutocompletePlace> getAutocompletePlace(@Query("input") String input, @Query("types") String types, @Query("key") String appid) ;

}
