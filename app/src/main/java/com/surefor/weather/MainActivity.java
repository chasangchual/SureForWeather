package com.surefor.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherInfoFragment.OnFragmentInteractionListener {
    @Bind(R.id.fragmentContainer) RelativeLayout fragmentContainer ;
    @Bind(R.id.viewpPager) ViewPager viewPager ;
    @Bind(R.id.pageIndicator)  CirclePageIndicator circlePageIndicator ;

    PageAdapter pageAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(fragmentContainer != null) {
            if(savedInstanceState != null) {
                return ;
            }

            WeatherInfoFragment weatherInfoFragment = WeatherInfoFragment.newInstance(null) ;
            // getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, weatherInfoFragment).commit() ;

            pageAdapter = new PageAdapter(this, viewPager) ;

            pageAdapter.add(WeatherInfoFragment.newInstance(null));
            pageAdapter.add(WeatherInfoFragment.newInstance(null));
            pageAdapter.add(WeatherInfoFragment.newInstance(null));
            pageAdapter.add(WeatherInfoFragment.newInstance(null));
            pageAdapter.add(WeatherInfoFragment.newInstance(null));
            pageAdapter.add(WeatherInfoFragment.newInstance(null));

            viewPager.setAdapter(pageAdapter);

            circlePageIndicator.setViewPager(viewPager);
            circlePageIndicator.setCurrentItem(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static class PageAdapter extends FragmentPagerAdapter {
        private final Context context;
        private final ViewPager viewPager;
        private List<WeatherInfoFragment> fragments = new ArrayList<>() ;

        public PageAdapter(MainActivity activity, ViewPager viewPager) {
            super(activity.getSupportFragmentManager());
            this.context = activity ;
            this.viewPager = viewPager ;
            this.viewPager.setAdapter(this);
        }

        @Override
        public Fragment getItem(int position) {
            if(position >= 0 && position < fragments.size()) {
                return fragments.get(position) ;
            }

            return null ;
        }

        @Override
        public int getCount() {
            return fragments.size() ;
        }

        public void add(WeatherInfoFragment weatherFragment) {
            fragments.add(weatherFragment);
        }
    }

}
