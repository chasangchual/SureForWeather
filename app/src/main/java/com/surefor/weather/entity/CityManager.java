package com.surefor.weather.entity;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * manages City information below.
 *  1) list of cities to retrieve weather data
 *  2) city most recently retrieved.
 *
 * Created by ethan on 05/11/2015.
 */
public class CityManager {
    private static CityManager instance = null ;
    private final String LIST_CANADA = "city.list.ca.json" ;
    private final String LIST_US = "city.list.us.json" ;
    private boolean isLoaded = false ;

    // private List<City> cities = new ArrayList<>() ;
    private Map<String, City> cities = new HashMap<>() ;

    // Singleton implementation
    public synchronized  static CityManager instance() {
        if (instance == null ) {
            instance = new CityManager() ;
        }
        return instance ;
    }

    // default constructor
    private CityManager() {

    }

    public List<String> getKeys() {
        return new ArrayList<String>(cities.keySet()) ;
    }

    public City getCity(String key) {
        return cities.get(key) ;
    }

    /**
     * purges city list
     */
    public void clear() {
        cities.clear();
        isLoaded = false ;
    }

    /**
     * shows how many city data loaded
     * @return number of cities
     */
    public Integer size() {
        return cities.size() ;
    }

    /**
     * loads a list of City class
     * @param asset project Assetmanager
     * @throws IOException
     */
    public void load(AssetManager asset) throws IOException {
        assert(asset != null) ;
        clear() ;
        load(new InputStreamReader(asset.open(LIST_CANADA))) ;
        load(new InputStreamReader(asset.open(LIST_US))) ;
    }

    /**
     *
     * @param in
     * @throws IOException
     */
    private void load(InputStreamReader in) throws IOException {
        Type listType = new TypeToken<List<City>>() {}.getType();
        Gson gson = new GsonBuilder().create() ;
        List<City> _cities = gson.fromJson(in, listType) ;

        for(City city  : _cities) {
            cities.put(city.getKey(), city) ;
        }

        isLoaded = true ;
    }

    public boolean isLoaded() {
        return isLoaded ;
    }

}
