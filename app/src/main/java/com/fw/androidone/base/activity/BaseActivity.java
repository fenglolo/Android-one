package com.fw.androidone.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * description : 基础activity
 * author : feng
 * date : 2021/3/4 13:44
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置默认隐藏标题栏
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//这个方法无效，当继承AppCompatActivity时，该方法无效；
        if (getSupportActionBar() != null) {
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
    }

    @Override
    protected void onDestroy() {
        //eventbus解注册
        EventBus.getDefault().unregister(this);
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
}
