package com.fw.androidone.activity.fragment;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.fragment.AnthorRightFragment;
import com.fw.androidone.fragment.RightFragment;

/**
 * description :
 * author : apple
 * date : 2021/3/8 11:25
 */
public class FragmentTest1Activity extends BaseActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_fragment_text_one;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_fragment);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_fragment) {
            replaceFragment(new AnthorRightFragment());
        }
    }

    //动态添加fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_layout, fragment);
        transaction.commit();
    }
}
