package com.surefor.weather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.surefor.weather.info.CityWeatherInfoPersist;
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

        new WeatherInfoLoadingWorker().execute() ;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    class WeatherInfoLoadingWorker extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            CityWeatherInfoPersist.load();

            return null ;
        }

        @Override
        protected void onPostExecute(Void value) {
            super.onPostExecute(value);

            Intent intent = new Intent(SplashActivity.this, MainActivity.class) ;
            startActivity(intent) ;
            finish() ;
        }
    }
}
