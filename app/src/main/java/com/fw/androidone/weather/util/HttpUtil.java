package com.fw.androidone.weather.util;

import com.fw.androidone.listener.http.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * description :网络请求工具类
 * author : apple
 * date : 2021/4/29 10:19
 */
public class HttpUtil {

    /**
     * OkHttp网络请求封装
     *
     * @param address  请求地址
     * @param callback 回调
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * HttpUrlConnection网络请求封装
     *
     * @param address  请求地址
     * @param listener 回调监听
     */
    public static void sendHttpRequest(String address, HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (null != listener) {
                        //回调成功方法
                        listener.onSuccess(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (null != listener) {
                        //回调失败方法
                        listener.onFail(e);
                    }
                } finally {
                    if (null != connection) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


}
