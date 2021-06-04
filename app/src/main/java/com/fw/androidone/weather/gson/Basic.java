package com.fw.androidone.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * description :
 * author : apple
 * date : 2021/4/30 13:49
 */
public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
