package com.fw.androidone.activity.network;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * description :OkHttp测试类
 * author : apple
 * date : 2021/4/7 11:27
 */
public class OkHttpActivity extends BaseActivity implements View.OnClickListener {
    private Button sendBtn;
    private TextView reponseText;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_okhttp;
    }

    @Override
    protected void initView() {
        sendBtn = findViewById(R.id.btn_send_request);
        reponseText = findViewById(R.id.tv_response);
    }

    @Override
    protected void initAction() {
        sendBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_request) {
            sendRequestWithOkHttp();
        }
    }

    //post请求
    private void sendRequestWithOkHttpPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", "admin")
                            .add("password", "123456")
                            .build();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    showResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //get请求
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    showResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在主线程进行ui操作
                reponseText.setText(response);
            }
        });
    }
}
