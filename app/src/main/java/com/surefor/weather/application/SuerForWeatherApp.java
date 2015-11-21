package com.surefor.weather.application;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

import com.couchbase.lite.CouchbaseLiteException;
import com.surefor.weather.utils.CouchDBSession;

import java.io.IOException;

/**
 * Created by ethan on 19/11/2015.
 */
public class SuerForWeatherApp extends Application {
    private CouchDBSession session ;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            session = CouchDBSession.instance(getApplicationContext()) ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
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
}
