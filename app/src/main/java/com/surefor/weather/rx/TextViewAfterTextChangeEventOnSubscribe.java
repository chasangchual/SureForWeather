package com.surefor.weather.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription ;

/**
 * Created by Ethan on 3/8/2016.
 */
public class TextViewAfterTextChangeEventOnSubscribe implements Observable.OnSubscribe<TextViewAfterTextChangeEvent> {

    final TextView view ;

    public TextViewAfterTextChangeEventOnSubscribe(TextView view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super TextViewAfterTextChangeEvent> subscriber) {
        MainThreadSubscription.verifyMainThread();

        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onNext(TextViewAfterTextChangeEvent.create(view, s));
                }
            }
        } ;

        view.addTextChangedListener(watcher);

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.removeTextChangedListener(watcher);
            }
        });

        // Emit initial value
        subscriber.onNext(TextViewAfterTextChangeEvent.create(view, view.getEditableText()));
    }
}
