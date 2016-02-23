package com.surefor.weather.utils;

import android.database.sqlite.SQLiteDatabase;

import com.surefor.weather.application.SuerForWeatherApp;

import java.io.File;

/**
 * Created by ethan on 23/11/2015.
 */
public class DataBaseUtils {
    public static Boolean exists(String name) {
        SQLiteDatabase liteDB = null;
        try {
            File database = SuerForWeatherApp.getInstance().getDatabasePath(name) ;
            if(database.exists()) {
                String path = database.getAbsolutePath() ;
                liteDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY) ;
            }
        } finally {
            if(liteDB != null) {
                liteDB.close();
            }
        }
        return liteDB != null ;
    }
}
