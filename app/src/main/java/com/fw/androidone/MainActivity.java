package com.fw.androidone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.activity.recycler.RecyclerActivity;
import com.fw.androidone.activity.recycler.RecyclerTestActivity;
import com.fw.androidone.base.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_recycler;
    private Button btn_recycler_text;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_recycler = findViewById(R.id.btn_recycler);
        btn_recycler_text = findViewById(R.id.btn_recycler_text);
    }

    @Override
    protected void initAction() {
        btn_recycler.setOnClickListener(this);
        btn_recycler_text.setOnClickListener(this::onClick);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_recycler) {
            startActivity(new Intent(this, RecyclerActivity.class));
        } else if (v.getId() == R.id.btn_recycler_text) {
            startActivity(new Intent(this, RecyclerTestActivity.class));
        }
    }
}