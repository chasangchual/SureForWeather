package com.surefor.weather;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.surefor.weather.api.GoogleMap;
import com.surefor.weather.api.OpenWeatherMap;
import com.surefor.weather.event.GetWeatherForecastEvent;
import com.surefor.weather.utils.ViewUtils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_splash);

        ViewUtils.setTypeFace((TextView) findViewById(R.id.txtSplashIcon), getAssets(), "fonts/meteocons.ttf") ;
        ViewUtils.setTypeFace((TextView) findViewById(R.id.txtSplashLabel), getAssets(), "fonts/gotham-light.ttf") ;

        ProgressBar pb = (ProgressBar) findViewById(R.id.pbSplash) ;
        pb.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY) ;
    }

    @Override
    protected void onResume() {
        super.onResume();

        OpenWeatherMap weatherMap = new OpenWeatherMap() ;
        GoogleMap googleMap = new GoogleMap() ;

//        googleMap.handleGetGeoCode(new GetAutocompletePlaceEvent().getRequest("oa"));


//        GetWeatherCurrentEvent.Request getCurrentWeatherrequest = new GetWeatherCurrentEvent.Request("Oakville, ON, Canada") ;
//        weatherMap.handleGetWeatherCurrent(getCurrentWeatherrequest);

        GetWeatherForecastEvent.Request getForecastWeatherrequest = new GetWeatherForecastEvent.Request("Oakville, ON, Canada") ;
        weatherMap.handleGetWeatherForecast(getForecastWeatherrequest);

//        GetWeatherForecastDailyEvent.Request getForecastDailyWeatherrequest = new GetWeatherForecastDailyEvent.Request("Oakville, ON, Canada") ;
//        weatherMap.handleGetWeatherForecastDaily(getForecastDailyWeatherrequest);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
