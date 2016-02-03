package com.surefor.weather.application;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.squareup.otto.Bus;
import com.surefor.weather.api.GoogleMap;
import com.surefor.weather.api.OpenWeatherMap;
import com.surefor.weather.event.BusProvider;

/**
 * Created by ethan on 19/11/2015.
 */
public class SuerForWeatherApp extends Application {
    private static SuerForWeatherApp instance ;
    private static Resources resources = null ;

    public static SuerForWeatherApp context() {
        return instance ;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // reserve app resource for future reference
        resources = getResources();

        // reserve app context for future refernce
        instance = (SuerForWeatherApp) getApplicationContext() ;

        /* initialize otto event bus */
        Bus bus = BusProvider.getBus() ;

        // Create an event listener for Google Geocode
        bus.register(new GoogleMap(bus)) ; // register google geo code retrofit manager
        bus.register(new OpenWeatherMap(bus)) ; // register google open weather map retrofit manager
        bus.register(this); // register global listener
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static Resources getAppResources() {
        return resources ;
    }
}
