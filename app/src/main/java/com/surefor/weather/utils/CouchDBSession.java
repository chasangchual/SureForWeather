package com.surefor.weather.utils;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

/**
 * Created by ethan on 19/11/2015.
 */
public class CouchDBSession {
    public static final String TAG = "CouchbaseEvents" ;
    public static final String DB_NAME = "sure-for-weather" ;

    private static CouchDBSession instance ;
    private static Context context ;
    private static Manager manager ;
    private static Database database ;

    public synchronized static CouchDBSession instance() throws IOException, CouchbaseLiteException, IllegalArgumentException {
        if(context == null) throw new IllegalArgumentException("Contex is not initialized.") ;

        return instance(context) ;
    }

    public synchronized static CouchDBSession instance(Context androidContext) throws IOException, CouchbaseLiteException {
        if(context != null) {
            context = androidContext ;
        }

        if(instance == null) {
            instance = new CouchDBSession(context) ;
        }
        return instance ;
    }

    private CouchDBSession(Context context) throws IOException, CouchbaseLiteException {
        manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS) ;
        database = manager.getDatabase("sure-for-weather") ;
    }

    public Manager getManager() {
        return manager ;
    }

    public Database getDatabase() {
        return database ;
    }
}
