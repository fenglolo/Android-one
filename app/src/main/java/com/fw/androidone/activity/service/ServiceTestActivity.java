package com.fw.androidone.activity.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.service.MyIntentService;
import com.fw.androidone.service.MyService;

/**
 * description :开启、关闭、绑定、解绑服务,使用IntentService服务
 * author : apple
 * date : 2021/4/12 13:54
 */
public class ServiceTestActivity extends BaseActivity implements View.OnClickListener {
    private Button startService, stopService;
    private Button bindService, unbindservice;
    private Button intentService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            //activity中调用service中的public方法
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected int getContentLayout() {
        return R.layout.activity_service_test;
    }

    @Override
    protected void initView() {
        startService = findViewById(R.id.btn_start_service);
        stopService = findViewById(R.id.btn_stop_service);
        bindService = findViewById(R.id.btn_bind_service);
        unbindservice = findViewById(R.id.btn_unbind_service);
        intentService = findViewById(R.id.btn_start_intent_service);
    }

    @Override
    protected void initAction() {
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindservice.setOnClickListener(this);
        intentService.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_service) {
            //启动服务
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (v.getId() == R.id.btn_stop_service) {
            //关闭服务
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        } else if (v.getId() == R.id.btn_bind_service) {
            //绑定服务
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, connection, BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.btn_unbind_service) {
            //解绑服务
            unbindService(connection);
        } else if (v.getId() == R.id.btn_start_intent_service) {
            //使用IntentService服务
            //打印主线程id
            Log.d("ServiceTestActivity", "Thread is =" + Thread.currentThread().getId());
            Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
        }
    }


}
