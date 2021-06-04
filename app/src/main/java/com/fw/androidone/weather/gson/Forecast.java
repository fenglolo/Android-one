package com.fw.androidone.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * description :
 * author : apple
 * date : 2021/4/30 13:59
 */
public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {
        public String max;
        public String min;
    }

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
