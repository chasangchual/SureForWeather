package com.surefor.weather.info;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.surefor.weather.application.SuerForWeatherApp;
import com.surefor.weather.event.GetWeatherCurrentEvent;
import com.surefor.weather.event.GetWeatherDailyForecastEvent;
import com.surefor.weather.event.GetWeatherForecastEvent;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import rx.functions.Action1;

/**
 * Created by Ethan on 2016-02-17.
 */
public class CityWeatherInfoPersist {
    public static final String WEATHER_INFO_FILE = "weather.txt" ;
    public static final String WEATHER_INFO_FILE_ENCODING = "UTF-8" ;

    /**
     * returns RxJava Action to persist current weather information
     */
    public static Action1<GetWeatherCurrentEvent.Response> getCurrentWeatherPersistAction() {
        return new Action1<GetWeatherCurrentEvent.Response>() {

            @Override
            public void call(GetWeatherCurrentEvent.Response response) {
                CityWeatherInfoCollection collection = CityWeatherInfoCollection.getInstance() ;
                collection.setWeatherCurrent(response.getName(), response.getObject());
                save() ;
            }
        } ;
    }

    /**
     * returns RxJava Action to persist weather forecast information
     */
    public static Action1<GetWeatherForecastEvent.Response> getWeatherForecastPersistAction() {
        return new Action1<GetWeatherForecastEvent.Response>() {

            @Override
            public void call(GetWeatherForecastEvent.Response response) {
                CityWeatherInfoCollection collection = CityWeatherInfoCollection.getInstance() ;
                collection.setWeatherForecast(response.getName(), response.getObject());
                save() ;
            }
        } ;
    }

    /**
     * returns RxJava Action to persist weather daily forecast information
     */
    public static Action1<GetWeatherDailyForecastEvent.Response> getWeatherDailyForecastPersistAction() {
        return new Action1<GetWeatherDailyForecastEvent.Response>() {

            @Override
            public void call(GetWeatherDailyForecastEvent.Response response) {
                CityWeatherInfoCollection collection = CityWeatherInfoCollection.getInstance() ;
                collection.setWeatherDailyForecast(response.getName(), response.getObject());
                save() ;
            }
        } ;
    }

    /**
     * delete weather information persist file
     */
    public static void delete() {
        try {
            Context context = SuerForWeatherApp.getInstance().getApplicationContext() ;
            File file = new File(context.getFilesDir(), WEATHER_INFO_FILE);

            boolean deleted = file.delete() ;
            deleted = context.deleteFile(WEATHER_INFO_FILE) ;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * persist weather information to internal storage
     */
    public static void save() {
        Context context = SuerForWeatherApp.getInstance().getApplicationContext() ;
        File file = new File(context.getFilesDir(), WEATHER_INFO_FILE);

        try {
            Map<String, CityWeatherInfo> weathers = CityWeatherInfoCollection.getInstance().getWeathers() ;
            String json = toJson(weathers) ;

            FileUtils.write(file, json, Charset.forName(WEATHER_INFO_FILE_ENCODING)) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load weather information from internal storage
     */
    public static void load() {
        Context context = SuerForWeatherApp.getInstance().getApplicationContext() ;
        File file = new File(context.getFilesDir(), WEATHER_INFO_FILE);

        try {
            load(FileUtils.readFileToString(file)) ;
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            // ignore FileNotFoundException exception.
        } catch (IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load weather information collection from json string
     */
    public static void load(String json) {
        Map<String, CityWeatherInfo> weathers = fromJson(json) ;
        load(weathers) ;
    }

    /**
     * load weather information collection from json string
     */
    public static void load(Map<String, CityWeatherInfo> weathers) {
        CityWeatherInfoCollection collection = CityWeatherInfoCollection.getInstance() ;
        collection.clear();

        Set<Map.Entry<String, CityWeatherInfo>> entities = weathers.entrySet() ;

        Iterator<Map.Entry<String, CityWeatherInfo>> itr = entities.iterator() ;
        while(itr.hasNext()) {
            Map.Entry<String, CityWeatherInfo> entity = itr.next() ;

            collection.setWeatherCurrent(entity.getKey(), entity.getValue().getCurrent());
            collection.setWeatherForecast(entity.getKey(), entity.getValue().getForecast());
            collection.setWeatherDailyForecast(entity.getKey(), entity.getValue().getDailyForecast());
        }
    }

    /**
     * convert weather information map to json string
     */
    public static String toJson(Map<String, CityWeatherInfo> weathers) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(weathers) ;
    }

    /**
     * create weather information map from json string
     */
    public static Map<String, CityWeatherInfo> fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        Type type = new TypeToken<Map<String, CityWeatherInfo>>(){}.getType();
        return gson.fromJson(json, type) ;
    }
}
