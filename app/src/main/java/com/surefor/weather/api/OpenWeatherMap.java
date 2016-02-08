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

    public static Action1<GetWeatherCurrentEvent.Request> getWeatherCurrentAction() {
        return  new Action1<GetWeatherCurrentEvent.Request>() {
            @Override
            public void call(GetWeatherCurrentEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

                Observable<WeatherCurrent> observable = null;

                if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
                    observable = client.getCurrent(request.getId());
                } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
                    observable = client.getCurrent(request.getName());
                }

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherCurrent>() {
                            @Override
                            public void call(WeatherCurrent weatherCurrent) {
                                GetWeatherCurrentEvent.Response response = new GetWeatherCurrentEvent.Response(weatherCurrent);
                                RxBus.getBus().post(response);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("handleGetWeatherCurrent", throwable.getMessage());
                            }
                        });
            }
        } ;
    }

    public static Action1<GetWeatherForecastEvent.Request> getWeatherForecastAction() {
        return  new Action1<GetWeatherForecastEvent.Request>() {
            @Override
            public void call(GetWeatherForecastEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

                Observable<WeatherForecast> observable = null;

                if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
                    observable = client.getForecast(request.getId());
                } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
                    observable = client.getForecast(request.getName());
                }

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherForecast>() {
                            @Override
                            public void call(WeatherForecast weatherForecast) {
                                // weatherForecast.getCity().getName() ;
                                GetWeatherForecastEvent.Response response = new GetWeatherForecastEvent.Response(weatherForecast);
                                RxBus.getBus().post(response);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("handleGetWeatherCurrent", throwable.getMessage()) ;
                            }
                        });
            }
        } ;
    }

    public static Action1<GetWeatherDailyForecastEvent.Request> getWeatherDailyForecastAction() {
        return  new Action1<GetWeatherDailyForecastEvent.Request>() {
            @Override
            public void call(GetWeatherDailyForecastEvent.Request request) {
                OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

                Observable<WeatherDailyForecast> observable = null;

                if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
                    observable = client.getDailyForecast(request.getId()) ;
                } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
                    observable = client.getDailyForecast(request.getName());
                }

                observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<WeatherDailyForecast>() {
                            @Override
                            public void call(WeatherDailyForecast weatherForecast) {
                                GetWeatherDailyForecastEvent event = new GetWeatherDailyForecastEvent();
                                event.getResponse(weatherForecast);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("handleGetWeatherCurrent", throwable.getMessage()) ;
                            }
                        });
            }
        } ;
    }

    /**
     * comment out Otto implementation as addressed in Otto github it has been deprecated
     * in favor of RxJava and RxAndroid. Methods below are transformed based on RxJava.
     *
    @Subscribe
    public void handleGetWeatherCurrent(GetWeatherCurrentEvent.Request request) {
        OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

        Observable<WeatherCurrent> observable = null;

        if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
            observable = client.getCurrent(request.getId());
        } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
            observable = client.getCurrent(request.getName());
        }

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherCurrent>() {
                    @Override
                    public void call(WeatherCurrent weatherCurrent) {
                        GetWeatherCurrentEvent event = new GetWeatherCurrentEvent();
                        event.getResponse(weatherCurrent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("handleGetWeatherCurrent", throwable.getMessage()) ;
                    }
                });
    }

     @Subscribe
    public void handleGetWeatherForecast(GetWeatherForecastEvent.Request request) {
        OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

        Observable<WeatherForecast> observable = null;

        if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
            observable = client.getForecast(request.getId());
        } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
            observable = client.getForecast(request.getName());
        }

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherForecast>() {
                    @Override
                    public void call(WeatherForecast weatherForecast) {
                        GetWeatherForecastEvent event = new GetWeatherForecastEvent();
                        event.getResponse(weatherForecast);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("handleGetWeatherCurrent", throwable.getMessage()) ;
                    }
                });
    }

     @Subscribe
    public void handleGetWeatherForecastDaily(GetWeatherDailyForecastEvent.Request request) {
        OpenWeatherMapClient client = OpenWeatherMapClient.getInstance();

        Observable<WeatherDailyForecast> observable = null;

        if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_ID) {
            observable = client.getDailyForecast(request.getId()) ;
        } else if (request.getRequestType() == GetWeatherCurrentEvent.REQUEST_TYPE.BY_NAME) {
            observable = client.getDailyForecast(request.getName());
        }

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherDailyForecast>() {
                    @Override
                    public void call(WeatherDailyForecast weatherForecast) {
                        GetWeatherDailyForecastEvent event = new GetWeatherDailyForecastEvent();
                        event.getResponse(weatherForecast);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("handleGetWeatherCurrent", throwable.getMessage()) ;
                    }
                });
    }
    */
}
