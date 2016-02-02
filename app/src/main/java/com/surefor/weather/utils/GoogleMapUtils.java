package com.surefor.weather.utils;

import com.surefor.weather.entity.geocode.AddressComponent;
import com.surefor.weather.entity.geocode.GeoCode;
import com.surefor.weather.entity.geocode.Result;
import com.surefor.weather.entity.place.AutocompletePlace;
import com.surefor.weather.entity.place.Prediction;
import com.surefor.weather.entity.place.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 2016-02-02.
 */
public class GoogleMapUtils {
    public static List<String> getCityLongNameAndCountryShortName(GeoCode geocode) {
        assert (geocode != null) ;

        List<String> cities = new ArrayList<String>() ;

        String city = "" ;
        String country = "" ;
        for(Result result : geocode.getResults()) {
            for(AddressComponent component : result.getAddressComponents()) {
                if(contains(component.getTypes(), "locality", "political")) {
                    city = component.getLongName() ;
                } else if(contains(component.getTypes(), "country", "political")) {
                    country = component.getShortName() ;
                }
            }
            cities.add(city + "," + country) ;
        }

        return cities ;
    }

    public static List<String> getCityLongNameAndCountryShortName(AutocompletePlace places) {
        assert (places != null) ;

        List<String> cities = new ArrayList<String>() ;

        String city = "" ;
        String country = "" ;

        for(Prediction prediction : places.getPredictions()) {
            if(contains(prediction.getTypes(), "locality", "political", "geocode")) {
                city = prediction.getTerms().get(0).getValue() ;
                country = prediction.getTerms().get(2).getValue() ;

                cities.add(city + "," + country) ;
            }
        }

        return cities ;
    }

    private static Boolean contains(List<String> list, String... strings) {
        Boolean contains = Boolean.TRUE ;

        for(int i = 0 ; i < strings.length && contains; i++) {
            if(!list.contains(strings[i])) {
                contains = false ;
            }
        }
        return contains ;
    }
}
