package com.fw.androidone;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fw.androidone.activity.SelectActivity;
import com.fw.androidone.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private ListView listView;
    private String[] data;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.lv_main);
    }

    @Override
    protected void initAction() {
        init();
    }

    @Override
    protected void initData() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                intent.putExtra("type", position + 1);
                startActivity(intent);
            }
        });
    }

    private void init() {
        data = new String[]{"UI开发",
                "Fragment",
                "全剧大喇叭-广播",
                "数据存储（持久化）",
                "登录",
                "动态权限申请",
                "内容提供器content provider",
                "通知Notification",
                "调用摄像头和相册",
                "播放多媒体文件"};
    }
}