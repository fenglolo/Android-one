package com.fw.androidone.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.fw.androidone.R;
import com.fw.androidone.adapter.recycler.FruitAdapter;
import com.fw.androidone.adapter.recycler.FruitHoriAdapter;
import com.fw.androidone.adapter.recycler.FruitWaterAdapter;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.recycler.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerActivity extends BaseActivity {

    private RecyclerView recyclerView1;
    private final List<Fruit> fruitList = new ArrayList<>();

    private RecyclerView recyclerView2;

    private RecyclerView recyclerView3;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {
        recyclerView1 = findViewById(R.id.rv);
        recyclerView2 = findViewById(R.id.rv2);
        recyclerView3 = findViewById(R.id.rv3);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        initFruit(false);

        //普通RecyclerView实现listview效果
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(manager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView1.setAdapter(adapter);

        //实现水平方向滚动
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(RecyclerView.HORIZONTAL);//设置水平方向滚动
        recyclerView2.setLayoutManager(manager1);
        FruitHoriAdapter adapter1 = new FruitHoriAdapter(fruitList);
        recyclerView2.setAdapter(adapter1);

//        initFruit(true);
//        //设置瀑布流，3列，orientation为VERTICAL
//        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView3.setLayoutManager(manager2);
//        FruitWaterAdapter adapter2 = new FruitWaterAdapter(fruitList);
//        recyclerView3.setAdapter(adapter2);

        //网格布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recyclerView3.setLayoutManager(gridLayoutManager);
        FruitWaterAdapter adapter3 = new FruitWaterAdapter(fruitList);
        recyclerView3.setAdapter(adapter3);
    }

    private void initFruit(boolean isLong) {
        for (int i = 0; i < 10; i++) {
            Fruit fruit = new Fruit(isLong ? getRandomLengthName("苹果") : "苹果", R.mipmap.menu_demo_barcode);
            fruitList.add(fruit);
            Fruit fruit2 = new Fruit(isLong ? getRandomLengthName("西瓜") : "西瓜", R.mipmap.menu_demo_behavior);
            fruitList.add(fruit2);
            Fruit fruit3 = new Fruit(isLong ? getRandomLengthName("水蜜桃") : "水蜜桃", R.mipmap.menu_demo_chart);
            fruitList.add(fruit3);
            Fruit fruit4 = new Fruit(isLong ? getRandomLengthName("西红柿") : "西红柿", R.mipmap.menu_demo_dialog);
            fruitList.add(fruit4);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}