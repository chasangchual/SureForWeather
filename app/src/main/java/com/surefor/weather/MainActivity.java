package com.surefor.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Bus;
import com.surefor.weather.event.BusProvider;
import com.surefor.weather.event.SearchAddress;

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

        Bus bus = BusProvider.getBus() ;
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Bus bus = BusProvider.getBus() ;
        bus.unregister(this);
    }
}
