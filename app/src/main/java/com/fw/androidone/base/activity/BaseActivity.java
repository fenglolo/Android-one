package com.fw.androidone.base.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fw.androidone.activity.login.LoginActivity;
import com.fw.androidone.utils.ActivityCollector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * description : 基础activity
 * author : feng
 * date : 2021/3/4 13:44
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LoginoutReceiver loginoutReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置默认隐藏标题栏
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//当继承AppCompatActivity时，该方法无效；
        if (getSupportActionBar() != null) {//当继承Activity时，该方法生效；
            getSupportActionBar().hide();
        }

        //eventbus注册
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (this.getContentLayout() != 0) {
            this.setContentView(this.getContentLayout());
        }
        this.initView();
        this.initAction();
        this.initData();

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginoutReceiver = new LoginoutReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.fw.androidone.FORCE_LOGIN_OUT");
        registerReceiver(loginoutReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(loginoutReceiver);
    }

    @Override
    protected void onDestroy() {
        //eventbus解注册
        EventBus.getDefault().unregister(this);
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }

    //设置加载布局
    protected abstract int getContentLayout();

    //初始化view
    protected abstract void initView();

    //初始化action
    protected abstract void initAction();

    //初始化数据
    protected abstract void initData();

    @Subscribe
    public void onEvent(Object object) {
    }

    static class LoginoutReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("温馨提示");
            builder.setMessage("您的账号已经在其他设备登录，请重新登录。");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();//销毁所有activity
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
