package com.surefor.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surefor.weather.info.CityWeatherInfo;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherInfoFragment extends Fragment {

    private static final String CITY_NAME = "city" ;
    private static final String TEMPERATURE = "temperature" ;

    @Bind(R.id.tvTemperature) TextView tvTemperature ;
    @Bind(R.id.tvCity) TextView tvCity ;

    private String cityName ;
    private Double temperature ;

    private OnFragmentInteractionListener mListener;

    public WeatherInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WeatherInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherInfoFragment newInstance(CityWeatherInfo weatherInfo) {
        WeatherInfoFragment fragment = new WeatherInfoFragment();
        Bundle args = new Bundle();

        if(weatherInfo != null) {
            args.putString(CITY_NAME, weatherInfo.getName());
            args.putDouble(TEMPERATURE, weatherInfo.getCurrent().getMain().getTemp());
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            cityName = getArguments().getString(CITY_NAME);
            temperature = getArguments().getDouble(TEMPERATURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_weatherinfo, container, false);

        ButterKnife.bind(this, fragmentView);

        // tvCity.setText(cityName);
        // tvTemperature.setText(String.valueOf(this.temperature) + " \u2109");
        return fragmentView ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
