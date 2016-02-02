package com.surefor.weather.db;

import android.content.Context;

import java.util.List;

/**
 * Created by Ethan on 2016-01-29.
 */
public class CityService {
    private CityService instance = null ;
    private CityDAO dao = null ;

    /**
     *
     * @param appContext
     * @return
     */
    public CityService getInstance(Context appContext) {
        if(instance == null) {
            instance = new CityService(appContext) ;
        }

        return instance ;
    }

    /**
     *
     * @return
     */
    public CityService getInstance() {
        if(instance == null) {
            throw new IllegalArgumentException("Please initialze instance with Android context first.") ;
        }

        return instance ;
    }

    /**
     *
     * @param appContext
     */
    private CityService(Context appContext) {
        dao = CityDAO.getInstance(appContext) ;
    }

    /**
     *
     * @param city
     */
    public void add(CityEntity city) {
        dao.insert(city.getName(), city.getCountry(), city.getLongitude(), city.getLatitude()) ;
    }

    public Integer count() {
        return dao.count() ;
    }

    /**
     *
     * @param name
     * @param country
     * @return
     */
    public CityEntity get(String name, String country) {
        return dao.get(name, country) ;
    }

    /**
     *
     * @return
     */
    public List<CityEntity> getAll() {
        return dao.getAll() ;
    }

    /**
     *
     * @param name
     * @param country
     * @return
     */
    public Boolean exists(String name, String country) {
        return dao.exists(name, country) ;
    }
}
