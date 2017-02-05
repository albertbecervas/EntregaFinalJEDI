package com.code.albert.evilmemory.interfaces;

import com.code.albert.evilmemory.adapter.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Albert on 03/02/2017.
 */
public interface WeatherService {


    @Headers({"Accept: application/json"})
    @GET("/version")
    Call<WeatherResponse> getVersion();

    @Headers({"Accept: application/json"})
    @GET("/cocksplat/{name}/{from}")
    Call<WeatherResponse> getCocksplat(@Path("name") String name,
                                     @Path("from") String from,
                                     @Query("i18n") String language,
                                     @Query("shoutcloud") String x);

    @Headers({"Accept: application/json"})
    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("APPID") String appid);


}
