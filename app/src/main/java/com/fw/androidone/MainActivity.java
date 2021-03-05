package com.fw.androidone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.activity.RecyclerActivity;
import com.fw.androidone.base.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_recycler;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_recycler = findViewById(R.id.btn_recycler);
    }

    @Override
    protected void initAction() {
        btn_recycler.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_recycler) {
            startActivity(new Intent(this, RecyclerActivity.class));
        }
    }
}