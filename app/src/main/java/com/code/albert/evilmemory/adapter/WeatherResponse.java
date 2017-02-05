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

    public String getWeathermain() {
        Log.d("weather", "getWeathermain: "+weathermain.toString());
        return weathermain.toString();
    }

    public Double getLat() {
        return coord.getLat();
    }

    public Double getLon() {
        return coord.getLon();
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getWeather() {
        return weathers.get(0).toString();
    }
}
