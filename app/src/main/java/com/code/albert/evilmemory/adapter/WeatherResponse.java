package com.code.albert.evilmemory.adapter;

import android.util.Log;

import com.code.albert.evilmemory.data.CoordData;
import com.code.albert.evilmemory.data.WeatherData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Albert on 03/02/2017.
 */
public class WeatherResponse {
    @SerializedName("message") String m;
    @SerializedName("subtitle") String s;
    @SerializedName("APPID") String appid;
    CoordData coord;
    @SerializedName("weather") List<WeatherData> weathers;
    @SerializedName("main") WeatherMainData weathermain;
    @SerializedName("name") String nam;

    public String getWeathermain() {
        String temp=weathermain.getTemp().toString();
        return temp;
    }

    public String getPressure(){
        String pres=weathermain.getPressure().toString();
        return pres;
    }

    public String getHumidity(){
        String hum=weathermain.getHumidity().toString();
        return hum;
    }

    public String getTempMin(){
        String mintemp=weathermain.getTemp_min().toString();
        return mintemp;
    }

    public String getTempMax(){
        String maxtemp=weathermain.getTemp_max().toString();
        return maxtemp;
    }

    public String getNam(){
        return nam;
    }



    public String getWeather() {
        return weathers.get(0).toString();
    }

}
