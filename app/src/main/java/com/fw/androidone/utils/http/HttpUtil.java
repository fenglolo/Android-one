package com.fw.androidone.utils.http;

import com.fw.androidone.listener.http.HttpCallbackListener;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description :网络请求工具类
 * author : apple
 * date : 2021/4/9 10:19
 */
public class HttpUtil {

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

    //=======================测试========================

    public void Test() {
        String address = "https://www.baidu.com";

        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onSuccess(String response) {
                //获取到服务器返回的数据，执行具体的操作
            }

            @Override
            public void onFail(Exception e) {
                //对异常进行处理
            }
        });

        HttpUtil.sendOkHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取到服务器返回的数据，执行具体的操作
                String data = response.body().string();
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //对异常进行处理
            }

        });
    }
}
