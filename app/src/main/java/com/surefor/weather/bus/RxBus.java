package com.surefor.weather.bus;

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
}
