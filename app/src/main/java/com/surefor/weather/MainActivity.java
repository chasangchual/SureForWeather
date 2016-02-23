package com.surefor.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(MainActivity.this, CitySearchActivity.class) ;
        startActivity(intent) ;
        finish() ;
/*
        RxBus.getBus().register(GetAutocompletePlaceEvent.Request.class, GoogleMap.getGetCodeAction()) ;

        RxBus.getBus().register(GetWeatherCurrentEvent.Request.class, OpenWeatherMap.getWeatherCurrentAction()) ;
        RxBus.getBus().register(GetWeatherForecastEvent.Request.class, OpenWeatherMap.getWeatherForecastAction()) ;
        RxBus.getBus().register(GetWeatherDailyForecastEvent.Request.class, OpenWeatherMap.getWeatherDailyForecastAction()) ;*/
    }

    @Override
    protected void onPause() {
        super.onPause();

/*        RxBus.getBus().unregister(GetAutocompletePlaceEvent.Request.class) ;

        RxBus.getBus().unregister(GetWeatherCurrentEvent.Request.class) ;
        RxBus.getBus().unregister(GetWeatherForecastEvent.Request.class) ;
        RxBus.getBus().unregister(GetWeatherDailyForecastEvent.Request.class) ;*/
    }
}
