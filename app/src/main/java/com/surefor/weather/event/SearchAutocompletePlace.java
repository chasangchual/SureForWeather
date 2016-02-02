package com.surefor.weather.event;

/**
 * Created by Ethan on 2016-02-02.
 */
public class SearchAutocompletePlace {
    private String place = "" ;

    public SearchAutocompletePlace(String place) {
        this.place = place ;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
