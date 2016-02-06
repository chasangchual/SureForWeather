package com.surefor.weather.api;

import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.geocode.GeoCode;
import com.surefor.weather.entity.place.AutocompletePlace;
import com.surefor.weather.event.GetAutocompletePlaceEvent;
import com.surefor.weather.event.SearchAddress;
import com.surefor.weather.utils.GoogleMapUtils;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Ethan on 2016-02-01.
 */
public class GoogleMap {

    public GoogleMap() {
    }

    public void handleGetGeoCode(SearchAddress address) {
        GoogleMapClient client = GoogleMapClient.getInstance() ;

        Observable<GeoCode> observable = client.getGeoCode(address.getAddress(), String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_google_geocode)));

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GeoCode>() {
                    @Override
                    public void call(GeoCode geoCode) {
                        List<String> cities = GoogleMapUtils.getCityLongNameAndCountryShortName(geoCode) ;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    public void handleGetGeoCode(GetAutocompletePlaceEvent.Request reqeust) {
        GoogleMapClient client = GoogleMapClient.getInstance() ;
        Observable<AutocompletePlace> observable = client.getAutocompletePlace(reqeust.getPlace(), "(cities)", String.valueOf(SuerForWeatherApp.getAppResources().getText(R.string.key_google_geocode)));

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AutocompletePlace>() {
                    @Override
                    public void call(AutocompletePlace autocompletePlace) {
                        List<String> cities = GoogleMapUtils.getCityLongNameAndCountryShortName(autocompletePlace);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });


    }
}
