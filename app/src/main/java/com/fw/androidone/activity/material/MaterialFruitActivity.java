package com.fw.androidone.activity.material;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * description :水果详情类
 * author : apple
 * date : 2021/4/21 10:09
 */
public class MaterialFruitActivity extends BaseActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView;

    private String fruitName;
    private int fruitImageId;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_material_test_fruit;
    }

    @Override
    protected void initView() {
        getIntentData();

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolBar);
        imageView = findViewById(R.id.fruit_image_view);
        textView = findViewById(R.id.fruit_content_text);
    }

    @Override
    protected void initAction() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void initData() {
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(imageView);//Glide加载图片
        String fruitContent = generateFruitContent(fruitName);
        textView.setText(fruitContent);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        fruitName = intent.getStringExtra(FRUIT_NAME);
        fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            builder.append(fruitName + " ");
        }
        return builder.toString();
    }
}
