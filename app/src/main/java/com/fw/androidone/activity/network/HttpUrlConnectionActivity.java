package com.fw.androidone.activity.network;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * description :HttpUrlConnection测试类
 * author : apple
 * date : 2021/4/7 11:27
 */
public class HttpUrlConnectionActivity extends BaseActivity implements View.OnClickListener {
    private Button sendBtn;
    private TextView reponseText;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_httpurlconnection;
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
            sendRequestWithHttpUrlConnection();
        }
    }

    private void sendRequestWithHttpUrlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    //get方式
                    connection.setRequestMethod("GET");
                    //post方式
//                    connection.setRequestMethod("POST");
//                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
//                    outputStream.writeBytes("username=admin&password=123456");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获取的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    showResponse(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
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
