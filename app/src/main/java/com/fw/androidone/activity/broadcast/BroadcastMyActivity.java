package com.fw.androidone.activity.broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :自定义广播测试
 * author : apple
 * date : 2021/3/12 10:24
 */
public class BroadcastMyActivity extends BaseActivity {
    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_broadcast_my;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_broadcast_my);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送自定义广播
                Intent intent = new Intent("com.fw.androidone.broadcast.MY_BROADCAST");
                intent.setComponent(new ComponentName("com.fw.androidone",
                        "com.fw.androidone.broadcast.MyBroadcastReceiver"));//参数一：包名 参数二：接收器路径
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
