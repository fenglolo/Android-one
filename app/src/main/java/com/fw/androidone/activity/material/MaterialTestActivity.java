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
import com.google.android.material.navigation.NavigationView;

/**
 * description : Material Design UI设计:Toolbar、DrawerLayout、NavigationView
 * author : apple
 * date : 2021/4/15 15:44
 */
public class MaterialTestActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);//设置navigationView默认选中call按钮
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_call) {
                    Toast.makeText(MaterialTestActivity.this, "点击了call", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_friends) {
                    Toast.makeText(MaterialTestActivity.this, "点击了friends", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_location) {
                    Toast.makeText(MaterialTestActivity.this, "点击了location", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_mail) {
                    Toast.makeText(MaterialTestActivity.this, "点击了mail", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_task) {
                    Toast.makeText(MaterialTestActivity.this, "点击了task", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }


}
