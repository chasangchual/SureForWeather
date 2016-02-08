package com.surefor.weather.bus;

import com.surefor.weather.event.GetWeatherCurrentEvent;
import com.surefor.weather.event.GetWeatherForecastEvent;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Ethan on 2016-02-07.
 */
public class RxBus {
    private final static Subject<java.lang.Object, java.lang.Object> bus = new SerializedSubject<>(PublishSubject.create());
    private static RxBus instance = null ;
    private Map<String, Subscription> subscribers = new HashMap<>() ;

    public static RxBus getBus() {
        if(instance == null) {
            instance = new RxBus() ;
        }
        return instance ;
    }

    public synchronized <T> Subscription register(final Class<T> eventClass, Action1<T> onNext) {
        Observable<T> o = bus.ofType(eventClass) ;
        Subscription s = o.subscribe(onNext) ;
        subscribers.put(eventClass.getName(), s) ;

        return s ;
    }

    public synchronized void unregister(final Class eventClass) {
        if(subscribers.containsKey(eventClass.getName())) {
            subscribers.get(eventClass.getName()).unsubscribe();
            subscribers.remove(eventClass.getName()) ;
        }
    }

    public void post(Object event) {
        bus.onNext(event);
    }

    public static void main(String[] args) {
        RxBus.getBus().register(GetWeatherCurrentEvent.Request.class, new Action1<GetWeatherCurrentEvent.Request>() {
            @Override
            public void call(GetWeatherCurrentEvent.Request request) {
                System.out.println(request.getId()) ;
            }
        }) ;

        RxBus.getBus().register(GetWeatherCurrentEvent.Request.class, new Action1<GetWeatherCurrentEvent.Request>() {
            @Override
            public void call(GetWeatherCurrentEvent.Request request) {
                System.out.println("------------------------------------") ;
            }
        }) ;

        RxBus.getBus().post(new GetWeatherCurrentEvent.Request(101L));
        RxBus.getBus().post(new GetWeatherForecastEvent.Request(201L));
        RxBus.getBus().post(new GetWeatherCurrentEvent.Request(102L));

        RxBus.getBus().unregister(GetWeatherCurrentEvent.Request.class);
        RxBus.getBus().post(new GetWeatherCurrentEvent.Request(103L));
        RxBus.getBus().post(new GetWeatherCurrentEvent.Request(104L));

        RxBus.getBus().register(GetWeatherForecastEvent.Request.class, new Action1<GetWeatherForecastEvent.Request>() {
            @Override
            public void call(GetWeatherForecastEvent.Request request) {
                System.out.println(request.getId());
            }
        }) ;

        RxBus.getBus().post(new GetWeatherForecastEvent.Request(202L));
        RxBus.getBus().post(new GetWeatherForecastEvent.Request(203L));
    }
}
