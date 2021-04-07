package com.fw.androidone.activity.webview;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :
 * author : apple
 * date : 2021/4/7 08:16
 */
public class WebviewActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web_view);
    }

    @Override
    protected void initAction() {
        initWebview();//webview初始化
    }

    @Override
    protected void initData() {
        // 从Android 9.0（API级别28）开始，默认情况下限制了明文流量的网络请求，
        // 对未加密流量不再信任，直接放弃请求，因此http的url均无法在webview中加载，https不受影响。
        webView.loadUrl("https://www.baidu.com");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebview() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}
