package com.fw.androidone.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :测试强制下线功能
 * author : apple
 * date : 2021/3/16 10:16
 */
public class LoginOutTestActivity extends BaseActivity {
    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login_out;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_login_out);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.fw.androidone.FORCE_LOGIN_OUT");
                sendBroadcast(intent);
            }
        });
    }
}
