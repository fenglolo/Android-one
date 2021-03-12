package com.fw.androidone.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * description :
 * author : apple
 * date : 2021/3/12 11:24
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
