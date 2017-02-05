package com.code.albert.evilmemory.data;

/**
 * Created by Albert on 03/02/2017.
 */
public class WeatherData {
    String main;
    String description;

    @Override
    public String toString() {
        return main + "\n" + description;
    }
}
