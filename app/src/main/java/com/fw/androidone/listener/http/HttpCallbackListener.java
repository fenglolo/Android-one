package com.fw.androidone.listener.http;

/**
 * description :HttpUrlConnection网络监听回调监听
 * author : apple
 * date : 2021/4/9 10:21
 */
public interface HttpCallbackListener {

    void onSuccess(String response);

    void onFail(Exception e);
}
