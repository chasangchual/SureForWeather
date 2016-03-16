package com.surefor.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.surefor.weather.api.GoogleMap;
import com.surefor.weather.api.GoogleMapService;
import com.surefor.weather.bus.RxBus;
import com.surefor.weather.entity.place.Prediction;
import com.surefor.weather.event.GetAutocompletePlaceEvent;
import com.surefor.weather.rx.TextViewAfterTextChangeEvent;
import com.surefor.weather.rx.TextViewAfterTextChangeEventOnSubscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CitySearchActivity extends AppCompatActivity {
    @Bind(R.id.txtSearchCity)  EditText txtSearch ;
    @Bind(R.id.lvCity) ListView listViewCity ;
    @BindString(R.string.key_google_geocode) String googleApiKey;
    CitySearchAutoCompleteAdapter adapter = null ;

    Observable<TextViewAfterTextChangeEvent> observableCityText ;
    Subscription googleAutoComplete ;

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
        observableCityText = Observable.create(new TextViewAfterTextChangeEventOnSubscribe(txtSearch)) ;

        listViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prediction prediction = adapter.getItem(position) ;
                prediction.getDescription() ; // get selected location name

                Intent intent = new Intent(CitySearchActivity.this, MainActivity.class) ;
                startActivity(intent) ;
                finish() ;

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        RxBus.getBus().register(GetAutocompletePlaceEvent.Request.class, GoogleMap.getGetAutoCompleteAction()) ;
        RxBus.getBus().register(GetAutocompletePlaceEvent.Response.class, getRefreshListAction()) ;

        googleAutoComplete = observableCityText.debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        Editable editable = textViewAfterTextChangeEvent.editable();
                        if (editable.toString().trim().length() > 0) {
                            RxBus.getBus().post(new GetAutocompletePlaceEvent.Request(editable.toString().trim()));
                        } else {
                            adapter.clear();
                        }
                    }
                }) ;
    }

    @Override
    protected void onPause() {
        super.onPause();

        googleAutoComplete.unsubscribe();
        RxBus.getBus().unregister(GetAutocompletePlaceEvent.Request.class);
        RxBus.getBus().unregister(GetAutocompletePlaceEvent.Response.class);
    }

    private Action1<GetAutocompletePlaceEvent.Response> getRefreshListAction() {
        return new Action1<GetAutocompletePlaceEvent.Response>() {
            @Override
            public void call(GetAutocompletePlaceEvent.Response response) {
                adapter.clear();
                if(response.getStatus().equalsIgnoreCase(GoogleMapService.STATUS_OK)) {
                    adapter.addAll(response.getPredictions());
                }
            }
        } ;
    }
}
