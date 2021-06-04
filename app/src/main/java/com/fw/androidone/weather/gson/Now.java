package com.fw.androidone.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * description :
 * author : apple
 * date : 2021/4/30 13:54
 */
public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
