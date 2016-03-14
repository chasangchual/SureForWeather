package com.surefor.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.surefor.weather.api.GoogleMapClient;
import com.surefor.weather.api.GoogleMapService;
import com.surefor.weather.entity.place.AutocompletePlace;
import com.surefor.weather.entity.place.Prediction;
import com.surefor.weather.rx.TextViewAfterTextChangeEvent;
import com.surefor.weather.rx.TextViewAfterTextChangeEventOnSubscribe;
import com.surefor.weather.utils.GoogleMapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

public class CitySearchActivity extends AppCompatActivity {
    @Bind(R.id.txtSearchCity)  EditText txtSearch ;
    // @Bind(R.id.listCity) ListView lvCity ;
    @BindString(R.string.key_google_geocode) String googleApiKey;
    CitySearchAutoCompleteAdapter adapter = null ;

    Observable<TextViewAfterTextChangeEvent> observable ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_city_search);
        ButterKnife.bind(this);

        List<Prediction> predictions = new ArrayList<>() ;
        adapter = new CitySearchAutoCompleteAdapter(this, predictions) ;

        ListView lvCity = (ListView) findViewById(R.id.lvCity) ;
        lvCity.setAdapter(adapter);

        // Observable<String> obserable = Observable<String>.create(new Observable<String>() {}) ;
        observable = Observable.create(new TextViewAfterTextChangeEventOnSubscribe(txtSearch)) ;

        observable.debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        adapter.clear();
                        Editable editable = textViewAfterTextChangeEvent.editable();
                        if(editable.toString().trim().length() > 0) {
                            GoogleMapClient apiClient = GoogleMapClient.getInstance();
                            Observable<AutocompletePlace> observable = apiClient.getAutocompletePlace(editable.toString(), "(cities)", googleApiKey);

                            observable.subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<AutocompletePlace>() {
                                        @Override
                                        public void call(AutocompletePlace autocompletePlace) {
                                            if(autocompletePlace.getStatus().equalsIgnoreCase(GoogleMapService.STATUS_OK)) {
                                                adapter.clear();
                                                adapter.addAll(autocompletePlace.getPredictions());
                                            }
                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {

                                        }
                                    });
                        }
                    }
                }) ;
    }
}
