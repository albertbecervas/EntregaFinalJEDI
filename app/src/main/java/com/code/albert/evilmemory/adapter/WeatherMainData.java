package com.code.albert.evilmemory.adapter;

import android.util.Log;

/**
 * Created by Albert on 05/02/2017.
 */

public class WeatherMainData {

    String temp;
    String pressure;
    String humidity;
    String temp_min;
    String temp_max;

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    /*@Override
    public String toString() {
        Log.d("temp", "toString: "+temp + "\n" + pressure + "\n" + humidity + "\n" + temp_min + "\n" + temp_max);

        return temp + "\n" + pressure + "\n" + humidity + "\n" + temp_min + "\n" + temp_max;
    }*/
}
