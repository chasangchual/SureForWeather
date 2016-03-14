package com.surefor.weather.rx;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.widget.TextView;
/**
 * Created by Ethan on 3/8/2016.
 */
public class TextViewAfterTextChangeEvent extends ViewEvent<TextView> {
    public static TextViewAfterTextChangeEvent create(@NonNull TextView view, @NonNull Editable editable) {
        return new TextViewAfterTextChangeEvent(view, editable) ;
    }

    private final Editable editable ;

    protected TextViewAfterTextChangeEvent(@NonNull TextView view, @NonNull Editable editable) {
        super(view);
        this.editable = editable ;
    }

    @NonNull
    public Editable editable() {
        return editable ;
    }

}
