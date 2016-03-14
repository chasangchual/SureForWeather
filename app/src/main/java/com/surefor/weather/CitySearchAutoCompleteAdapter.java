package com.surefor.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.surefor.weather.entity.place.Prediction;

import java.util.List;

/**
 * Created by Ethan on 3/7/2016.
 */
public class CitySearchAutoCompleteAdapter extends ArrayAdapter<Prediction> {

    public CitySearchAutoCompleteAdapter(Context context, List<Prediction> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        Prediction prediction = getItem(position) ;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_search_item, parent, false) ;
        }

        TextView tvCity = (TextView) convertView.findViewById(R.id.txtCity);
        TextView tvCountry = (TextView) convertView.findViewById(R.id.txtCountry);

        tvCity.setText(prediction.getTerms().get(0).getValue());
        tvCountry.setText(prediction.getDescription()) ;

        return convertView ;
    }
}
