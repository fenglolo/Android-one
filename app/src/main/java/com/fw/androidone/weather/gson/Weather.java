package com.fw.androidone.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/4/30 14:01
 */
public class Weather {
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
