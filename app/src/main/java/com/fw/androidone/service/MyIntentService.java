package com.fw.androidone.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * description :IntentService:可以在onHandleIntent处理复杂逻辑，该方法已经在子线程中，不用担心ANR问题
 * author : apple
 * date : 2021/4/13 15:01
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造方法
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的id
        Log.d("MyIntentService", "MyIntentService---Thread is =" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("MyIntentService", "MyIntentService---onDestroy");
    }
}
