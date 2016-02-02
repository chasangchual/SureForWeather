package com.surefor.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * provides database and table access operations such as open/close connection, create db/tables,
 * execute query, retrieve records, etc <br>
 *
 * Created by ethan on 22/11/2015.
 */
public class CityDAO extends SQLiteOpenHelper {
    public static final String DATABASE = "sure.for.weather" ;
    public static final Integer VERSION = 1 ;

    private static final String SQL_CREATE_CITY = "CREATE TABLE city (name TEXT NOT NULL, country TEXT NOT NULL, longitude REAL, latitude REAL, PRIMARY KEY(name, country)) ;" ;
    private static final String SQL_DROP_CITY = "DROP TABLE IF EXISTS city ;" ;

    private static final String SQL_COUNT_CITY = " SELECT count(*) FROM city ;" ;
    private static final String SQL_EXIST_CITY = " SELECT COUNT(*) FROM city WHERE name = ? AND country = ?  ;" ;
    private static final String SQL_SELECT_ALL = " SELECT name, country, longitude, latitude FROM city ;" ;

    private static CityDAO instance = null ;

    public static final String T_CITY = "city" ;
    public static final String C_CITY_NAME = "name" ;
    public static final String C_CITY_COUNTRY = "country" ;
    public static final String C_CITY_LAT = "latitude" ;
    public static final String C_CITY_LON = "longitude" ;

    /**
     * Singleton implementation
     * @param appContext Andorid application context which is required from SQLite. <br>
     *                   If application context has already set before, it ignores later one.
     * @return CityDAO instance
     */
    public static synchronized CityDAO getInstance(Context appContext) {
        // Singleton implementation
        if(instance == null) {
            instance = new CityDAO(appContext) ;
        }

        return instance;
    }

    /**
     * Singleton implementation.<br>
     * It assumes instance(Context appContext) was called before and it does not need application context now.
     *
     * @return CityDAO instance
     */
    public static synchronized CityDAO getInstance() {
        if(instance == null) {
            throw new IllegalArgumentException("Please initialze instance with Android context first.") ;
        }
        return instance;
    }

    /**
     * private constructor.
     * @param context Android application context
     */
    private CityDAO(Context context) {
        super(context, DATABASE, null, VERSION) ;
    }

    public Boolean existRequiredTables()
    {
        return existTable("city") ;
    }

    /**
     * validates if the table exists
     * @param tbname table name to be validated
     * @return TRUE if the table exists, FALSE otherwise
     */
    public Boolean existTable(String tbname) {
        String SQL = "SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' name = ? " ;
        String[] args = {tbname} ;
        Boolean exist = Boolean.FALSE ;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(SQL, args) ;
            if(!cursor.moveToFirst()) {
                return false ;
            }
            int count = cursor.getInt(0) ;
            cursor.close();

            exist = count > 0 ;

        } catch(Exception e1) {
            // Some Samsung phones have published with sqlite_master table removed.
            try {
                // if it retrieves records from the table without exception.
                db.rawQuery("SELECT * FROM " + tbname, null);
                exist = Boolean.TRUE ;
            } catch(Exception e2) {
                exist = Boolean.FALSE ;
            }
        }

        return exist ;
    }

    /**
     * inserts records into the table
     */
    public void insert(String name, String country, Double longitude, Double latitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_CITY_NAME, name);
        values.put(C_CITY_COUNTRY, country);
        values.put(C_CITY_LON, longitude);
        values.put(C_CITY_LAT, latitude);

        db.insert(T_CITY, null, values) ;
        db.close();
    }

    /**
     * return number of record in the city table.
     */
    public Integer count() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     *
     * @param name
     * @param country
     * @return
     */
    public CityEntity get(String name, String country) {
        SQLiteDatabase db = this.getReadableDatabase();
        CityEntity city = null ;

        Cursor cursor = db.query(SQL_EXIST_CITY, new String[] { C_CITY_NAME, C_CITY_COUNTRY, C_CITY_LON, C_CITY_LAT }, C_CITY_NAME + " = ? AND " + C_CITY_COUNTRY + "= ?",
                new String[] { name, country }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            city = new CityEntity(cursor.getString(0), cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3)) ;
        }

        return city ;
    }

    /**
     *
     * @return
     */
    public List<CityEntity> getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<CityEntity> cities = new ArrayList<>() ;

        Cursor cursor = db.rawQuery(SQL_SELECT_ALL, null) ;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                CityEntity city = new CityEntity(cursor.getString(0), cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3)) ;
                cities.add(city) ;
            } while(cursor.moveToNext()) ;
        }

        return cities ;
    }

    public Boolean exists(String name, String country) {
        return get(name, country) != null ;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_CITY);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
