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

import com.squareup.otto.Bus;
import com.surefor.weather.event.BusProvider;
import com.surefor.weather.event.SearchAddress;
import com.surefor.weather.event.SearchAutocompletePlace;
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bus bus = BusProvider.getBus() ;
        bus.register(this);

        bus.post(new SearchAutocompletePlace("oa"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Bus bus = BusProvider.getBus() ;
        bus.unregister(this);
    }
}
