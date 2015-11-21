package com.surefor.weather.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by ethan on 19/11/2015.
 */
public class ViewUtils {
    public static void setTypeFace(TextView tv, AssetManager assets, String font)
    {
        Typeface typeFace = Typeface.createFromAsset(assets, font) ;
        tv.setTypeface(typeFace);   
    }
}
