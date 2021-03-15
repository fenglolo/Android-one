package com.fw.androidone.activity.filepersistenec;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :sharedpreferences进行数据存取
 * author : apple
 * date : 2021/3/15 13:50
 */
public class SharedTest2Activity extends BaseActivity {

    private Button btn_save;
    private Button btn_get;
    private TextView textView;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NAME = "name";
    private final String AGE = "age";
    private final String MARRIED = "married";

    @Override
    protected int getContentLayout() {
        return R.layout.activity_file_test_two;
    }

    @Override
    protected void initView() {
        btn_save = findViewById(R.id.btn_shared_save);
        btn_get = findViewById(R.id.btn_shared_get);
        textView = findViewById(R.id.tv_shared_data);
    }

    @Override
    protected void initAction() {
        initShared();
    }

    @Override
    protected void initData() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedSave();
            }
        });
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedGet();
            }
        });
    }

    //sharedPreference初始化
    private void initShared() {
        preferences = getSharedPreferences("data", MODE_PRIVATE);
    }

    //sharedPreference存储
    private void sharedSave() {
        editor = preferences.edit();
        editor.putString(NAME, "Tony");
        editor.putInt(AGE, 18);
        editor.putBoolean(MARRIED, false);
        editor.apply();
    }

    //sharedPreference读取
    @SuppressLint("SetTextI18n")
    private void sharedGet() {
        String name = preferences.getString(NAME, "Li");
        int age = preferences.getInt(AGE, 0);
        boolean isMarry = preferences.getBoolean(MARRIED, false);

        textView.setText(name + "---" + age + "---" + isMarry);
        Log.d("SharedTest2Activity", "name is :" + name);
        Log.d("SharedTest2Activity", "age is :" + age);
        Log.d("SharedTest2Activity", "isMarry is :" + isMarry);
    }

}
