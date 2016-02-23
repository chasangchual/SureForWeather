package com.surefor.weather.info;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Ethan on 2016-02-15.
 */

@RunWith(MockitoJUnitRunner.class)
public class InfoManagerTest {
    private final String CITY = "Oakville, ON, Canada" ;

    WeatherCurrent current ;
    WeatherForecast forecast ;
    WeatherDailyForecast dailyForecast ;

    @Before
    public void setup() {

        try {
            InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CityWeatherInfoPersist.WEATHER_INFO_FILE));
            BufferedReader br = new BufferedReader(isr);

            GsonBuilder gsonBuilder = new GsonBuilder() ;
            Gson gson = gsonBuilder.create() ;

            current = gson.fromJson(br.readLine(), WeatherCurrent.class) ;
            forecast = gson.fromJson(br.readLine(), WeatherForecast.class) ;
            dailyForecast = gson.fromJson(br.readLine(), WeatherDailyForecast.class) ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPersisteWeatherInfo() {
        CityWeatherInfoCollection infoManager = CityWeatherInfoCollection.getInstance();

        infoManager.setWeatherCurrent(CITY, current);
        infoManager.setWeatherForecast(CITY, forecast);
        infoManager.setWeatherDailyForecast(CITY, dailyForecast);

        assertTrue(infoManager.count() == 1);
        assertTrue(infoManager.contains(CITY));

        String json = CityWeatherInfoPersist.toJson(infoManager.getWeathers()) ;

        infoManager.clear();
        assertTrue(infoManager.count() == 0);
        assertFalse(infoManager.contains(CITY));

        CityWeatherInfoPersist.load(CityWeatherInfoPersist.fromJson(json));

        assertTrue(infoManager.count() == 1);
        assertTrue(infoManager.contains(CITY));
    }
}
