package com.surefor.weather.rx;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * A target view on which an event occurred (e.g. click)
 *
 * Created by Ethan on 3/8/2016.
 */
public abstract class ViewEvent<T extends View> {
    private final T view ;

    protected ViewEvent(@NonNull T view) {
        this.view = view;
    }

    public @NonNull T view() {
        return view ;
    }
}
