package com.fw.androidone.activity.notification;

import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.utils.NotificationManagerUtils;

/**
 * description :通知测试
 * author : apple
 * date : 2021/3/29 10:45
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_notifi);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_notifi) {
            NotificationManagerUtils.startNotificationManager(this, "通知",
                    "这是一个notification测试", R.mipmap.ic_launcher);
        }
    }


}
