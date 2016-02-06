package com.surefor.weather.api;

import com.surefor.weather.R;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.entity.geocode.GeoCode;
import com.surefor.weather.entity.place.AutocompletePlace;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Ethan on 2016-02-01.
 */
public class GoogleMapClient implements GoogleMapService {
    private GoogleMapService service = null ;
    private static GoogleMapClient instance = null ;

    public synchronized static GoogleMapClient getInstance() {
        if(instance == null) {
            instance = new GoogleMapClient() ;
        }
        return instance ;
    }

    private GoogleMapClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(SuerForWeatherApp.getAppResources().getText(R.string.url_google_geocode).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        service = retrofit.create(GoogleMapService.class) ;
    }

    @Override
    public Observable<GeoCode> getGeoCode(@Query("address") String address, @Query("key") String appid) {
        return service.getGeoCode(address, appid) ;
    }

    @Override
    public Observable<AutocompletePlace> getAutocompletePlace(@Query("input") String input, @Query("types") String types, @Query("key") String appid) {
        return service.getAutocompletePlace(input, types, appid) ;
    }
}
