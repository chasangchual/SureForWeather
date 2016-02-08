package com.surefor.weather.application;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;

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

        // reserve app context for future reference
        instance = (SuerForWeatherApp) getApplicationContext() ;
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
