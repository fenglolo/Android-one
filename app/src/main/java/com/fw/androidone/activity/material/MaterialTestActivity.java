package com.fw.androidone.activity.material;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description : Material Design UI设计:Toolbar、DrawerLayout
 * author : apple
 * date : 2021/4/15 15:44
 */
public class MaterialTestActivity extends BaseActivity {

    private DrawerLayout drawerLayout;

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
        } else if (item.getItemId() == android.R.id.home) {//HomeAsUp按钮的id永远都是android.R.id.home
            drawerLayout.openDrawer(GravityCompat.START);//打开侧滑菜单
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

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);//设置ActionBar的HomeAsUp是否展示
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu);//设置HomeAsUp的图标
        }
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }


}
