package com.fw.androidone.weather.gson;

/**
 * description :
 * author : apple
 * date : 2021/4/30 13:52
 */
public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
