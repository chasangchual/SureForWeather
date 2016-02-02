package com.surefor.weather.event;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Ethan on 2016-02-02.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY) ;

    public static Bus getBus() {
        return BUS ;
    }

    private BusProvider() {

    }
}
