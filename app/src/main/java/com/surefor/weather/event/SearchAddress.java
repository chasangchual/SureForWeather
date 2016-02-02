package com.surefor.weather.event;

/**
 * Created by Ethan on 2016-02-01.
 */
public class SearchAddress {
    private String address ;
    public SearchAddress(String address) {
        this.address = address ;
    }

    public String getAddress() {
        return this.address ;
    }
}
