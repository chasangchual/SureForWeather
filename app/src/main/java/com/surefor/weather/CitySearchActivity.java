package com.surefor.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CitySearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_city_search);
    }
}
