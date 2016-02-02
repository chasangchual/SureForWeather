package com.surefor.weather.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 2016-02-02.
 */
public class LoadedAutocompletePlace {
    private List<String> cities = new ArrayList<>() ;

    public LoadedAutocompletePlace(List<String> cities) {
        cities.addAll(cities) ;
    }

    public void set(List<String> cities) {
        cities.clear();
        cities.addAll(cities) ;
    }

    public List<String> getAll() {
        return cities ;
    }

    public Integer size() {
        return cities.size() ;
    }

    public String get(Integer index) {
        if(index < 0 || index >= size()) {
            throw new IllegalArgumentException("out of range, " + String.valueOf(index));
        }

        return cities.get(index) ;
    }
}
