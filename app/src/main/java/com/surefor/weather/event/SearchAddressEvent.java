package com.surefor.weather.event;

/**
 * Created by Ethan on 2016-02-01.
 */
public class SearchAddressEvent {
    private String address ;
    public SearchAddressEvent(String address) {
        this.address = address ;
    }

    public String getAddress() {
        return this.address ;
    }
}
