package com.surefor.weather.api;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.geocode.GeoCode;
import com.surefor.weather.entity.place.AutocompletePlace;
import com.surefor.weather.event.LoadedAutocompletePlace;
import com.surefor.weather.event.SearchAddress;
import com.surefor.weather.event.SearchAutocompletePlace;
import com.surefor.weather.utils.GoogleMapUtils;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ethan on 2016-02-01.
 */
public class GoogleMap {
    private Bus bus = null ;

    public GoogleMap(Bus bus) {
        this.bus = bus ;
    }

    @Subscribe
    public void handleGetGeoCode(SearchAddress address) {
        GoogleMapClient client = GoogleMapClient.getInstance() ;

        Callback<GeoCode> callback = new Callback<GeoCode>() {
            @Override
            public void onResponse(Response<GeoCode> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    GeoCode geoCode = response.body() ;
                    List<String> cities = GoogleMapUtils.getCityLongNameAndCountryShortName(geoCode) ;
                    for(String city : cities) {

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        Call<GeoCode> call = client.getGeoCode(address.getAddress(), String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_google_geocode)));
        call.enqueue(callback);
    }

    @Subscribe
    public void handleGetGeoCode(SearchAutocompletePlace place) {
        GoogleMapClient client = GoogleMapClient.getInstance() ;

        Callback<AutocompletePlace> callback = new Callback<AutocompletePlace>() {
            @Override
            public void onResponse(Response<AutocompletePlace> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    AutocompletePlace autocompletePlaces = response.body() ;
                    List<String> cities = GoogleMapUtils.getCityLongNameAndCountryShortName(autocompletePlaces) ;
                    bus.post(new LoadedAutocompletePlace(cities));
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        Call<AutocompletePlace> call = client.getAutocompletePlace(place.getPlace(), "geocode", String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_google_geocode)));
        call.enqueue(callback);
    }
}
