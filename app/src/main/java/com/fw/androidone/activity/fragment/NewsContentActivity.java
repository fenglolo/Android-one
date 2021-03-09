package com.fw.androidone.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.fragment.NewsContentFragment;

/**
 * description :
 * author : apple
 * date : 2021/3/8 11:25
 */
public class NewsContentActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getContentLayout() {
        return R.layout.news_content;
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("news_title");
        String msg = getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(title, msg);
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

    public static void actionStart(Context context, String title, String content) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", title);
        intent.putExtra("news_content", content);
        context.startActivity(intent);
    }
}
