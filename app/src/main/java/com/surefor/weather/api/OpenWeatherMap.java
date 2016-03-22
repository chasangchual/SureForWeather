package com.surefor.weather.api;

import android.util.Log;

import com.surefor.weather.bus.RxBus;
import com.surefor.weather.entity.current.WeatherCurrent;
import com.surefor.weather.entity.dailyforecast.WeatherDailyForecast;
import com.surefor.weather.entity.forecast.WeatherForecast;
import com.surefor.weather.event.GetWeatherCurrentEvent;
import com.surefor.weather.event.GetWeatherDailyForecastEvent;
import com.surefor.weather.event.GetWeatherForecastEvent;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Ethan on 2016-02-03.
 */
public class OpenWeatherMap {

    public OpenWeatherMap() {
    }

    /**
     * returns RxJava Action to handle current weather information request
     */
    public static Action1<GetWeatherCurrentEvent.Request> getWeatherCurrentAction() {
        return  new Action1<GetWeatherCurrentEvent.Request>() {
            @Override
            public void call(final GetWeatherCurrentEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();
                Observable<WeatherCurrent> observable = client.getCurrent(request.getName());

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherCurrent>() {
                            @Override
                            public void call(WeatherCurrent weatherCurrent) {
                                GetWeatherCurrentEvent.Response response = new GetWeatherCurrentEvent.Response(request.getName(), weatherCurrent);
                                RxBus.getBus().post(response);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("OpenWeatherMap", throwable.getMessage());
                            }
                        });
            }
        } ;
    }

    /**
     * returns RxJava Action to handle weather forecast information request
     */
    public static Action1<GetWeatherForecastEvent.Request> getWeatherForecastAction() {
        return  new Action1<GetWeatherForecastEvent.Request>() {
            @Override
            public void call(final GetWeatherForecastEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

                Observable<WeatherForecast> observable = client.getForecast(request.getName());

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherForecast>() {
                            @Override
                            public void call(WeatherForecast weatherForecast) {
                                GetWeatherForecastEvent.Response response = new GetWeatherForecastEvent.Response(request.getName(), weatherForecast);
                                RxBus.getBus().post(response);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("OpenWeatherMap", throwable.getMessage()) ;
                            }
                        });
            }
        } ;
    }

    /**
     * returns RxJava Action to handle weather daily forecast information request
     */
    public static Action1<GetWeatherDailyForecastEvent.Request> getWeatherDailyForecastAction() {
        return  new Action1<GetWeatherDailyForecastEvent.Request>() {
            @Override
            public void call(final GetWeatherDailyForecastEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

                Observable<WeatherDailyForecast> observable = client.getDailyForecast(request.getName()) ;

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherDailyForecast>() {
                            @Override
                            public void call(WeatherDailyForecast weatherDailyForecast) {
                                GetWeatherDailyForecastEvent.Response response = new GetWeatherDailyForecastEvent.Response(request.getName(), weatherDailyForecast);
                                RxBus.getBus().post(response);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("OpenWeatherMap", throwable.getMessage()) ;
                            }
                        });
            }
        } ;
    }
}
