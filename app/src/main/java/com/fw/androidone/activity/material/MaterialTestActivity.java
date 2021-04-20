package com.fw.androidone.activity.material;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fw.androidone.R;
import com.fw.androidone.adapter.material.FruitNewAdapter;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.material.Fruits;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * description : Material Design UI设计:Toolbar、DrawerLayout、NavigationView
 * author : apple
 * date : 2021/4/15 15:44
 */
public class MaterialTestActivity extends BaseActivity {

    private DrawerLayout drawerLayout;//滑动菜单
    private NavigationView navigationView;//滑动菜单布局
    private FloatingActionButton fab;//悬浮按钮

    private RecyclerView recyclerView;
    private FruitNewAdapter fruitNewAdapter;
    private List<Fruits> fruitsList = new ArrayList<>();
    private Fruits[] fruits = {new Fruits("苹果", R.mipmap.fruit_apple),
            new Fruits("香蕉", R.mipmap.fruit_banana),
            new Fruits("菠萝", R.mipmap.fruit_boluo),
            new Fruits("橙子", R.mipmap.fruit_chenzi),
            new Fruits("胡萝卜", R.mipmap.fruit_huluobo),
            new Fruits("火龙果", R.mipmap.fruit_huolongguo),
            new Fruits("柠檬", R.mipmap.fruit_lemon),
            new Fruits("猕猴桃", R.mipmap.fruit_mihongtao),
            new Fruits("桃子", R.mipmap.fruit_peal),
            new Fruits("葡萄", R.mipmap.fruit_putao),
            new Fruits("西瓜", R.mipmap.fruit_xigua),
            new Fruits("西红柿", R.mipmap.fruit_xihongshi),
            new Fruits("樱桃", R.mipmap.fruit_yintao)};

    private SwipeRefreshLayout swipeRefreshLayout;//下拉刷新

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

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //滑动菜单布局
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

        //悬浮按钮
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "数据删除", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MaterialTestActivity.this, "数据恢复", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }
        });

        //RecyclerView
        initFruit();
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        fruitNewAdapter = new FruitNewAdapter(fruitsList);
        recyclerView.setAdapter(fruitNewAdapter);

        //SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.teal_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    private void initFruit() {
        fruitsList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitsList.add(fruits[index]);
        }
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        fruitNewAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

}
