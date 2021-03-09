package com.fw.androidone.activity.fragment;

import android.view.View;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :
 * author : apple
 * date : 2021/3/8 11:25
 */
public class NewsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getContentLayout() {
        // 在res目录新建一个layout-sw200dp文件夹，当设备屏幕宽度超过200dp的时候，
        // 就会加载layout-sw200dp目录下的news_main.xml；
        // 否则则就默认加载layout目录下的news_main.xml；
        return R.layout.news_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initAction() {
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {

    }
}
