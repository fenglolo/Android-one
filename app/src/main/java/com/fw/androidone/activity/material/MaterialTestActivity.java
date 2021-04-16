package com.fw.androidone.activity.material;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description : Material Design UI设计:Toolbar
 * author : apple
 * date : 2021/4/15 15:44
 */
public class MaterialTestActivity extends BaseActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.backup) {
            Toast.makeText(MaterialTestActivity.this, "点击了backup", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete) {
            Toast.makeText(MaterialTestActivity.this, "点击了delete", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.setting) {
            Toast.makeText(MaterialTestActivity.this, "点击了setting", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_material_test;
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }


}
