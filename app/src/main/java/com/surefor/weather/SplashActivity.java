package com.surefor.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.surefor.weather.entity.CityManager;
import com.surefor.weather.utils.ViewUtils;

import java.io.IOException;

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

        new LoadCities(getAssets()).execute() ;
    }

    class LoadCities extends AsyncTask<Void, Void, Integer>
    {
        AssetManager assetManager ;

        public LoadCities(AssetManager assetManager) {
            this.assetManager = assetManager ;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            CityManager manager = CityManager.instance() ;
            try {
                manager.load(assetManager);
            }
            catch (IOException ex) {

            }
            return manager.size() ;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class) ;
            startActivity(intent);

            finish();
        }
    }
}
