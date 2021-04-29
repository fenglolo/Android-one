package com.fw.androidone.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

/**
 * description :
 * author : apple
 * date : 2021/3/12 11:24
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //分包
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        init();
    }

    public static Context getContext() {
        return context;
    }

    private void init() {
        initLitePal();
    }

    private void initLitePal() {
        //数据库初始化
        LitePal.initialize(this);
    }
}
