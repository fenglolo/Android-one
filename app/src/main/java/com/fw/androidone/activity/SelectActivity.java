package com.fw.androidone.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.activity.broadcast.BroadcastMyActivity;
import com.fw.androidone.activity.broadcast.BroadcastTestActivity;
import com.fw.androidone.activity.filepersistenec.FileTest1Activity;
import com.fw.androidone.activity.filepersistenec.SharedTest2Activity;
import com.fw.androidone.activity.fragment.FragmentTest1Activity;
import com.fw.androidone.activity.fragment.NewsActivity;
import com.fw.androidone.activity.recycler.RecyclerActivity;
import com.fw.androidone.activity.recycler.RecyclerTestActivity;
import com.fw.androidone.adapter.SelectAdapter;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/8 13:39
 */
public class SelectActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private SelectAdapter adapter;
    private List<Select> list = new ArrayList<>();

    private int type;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.rv_select);
    }

    @Override
    protected void initAction() {
        list.clear();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        if (type == 1) {
            init1();
        } else if (type == 2) {
            init2();
        } else if (type == 3) {
            init3();
        } else if (type == 4) {
            init4();
        }
    }

    @Override
    protected void initData() {
        manager = new LinearLayoutManager(SelectActivity.this);
        recyclerView.setLayoutManager(manager);
        adapter = new SelectAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(new SelectAdapter.ItemClickListener() {
            @Override
            public void onClick(int position) {
                Select select = list.get(position);
                startActivity(new Intent(SelectActivity.this, select.getActivity().getClass()));
            }
        });
    }

    private void init1() {
        Select select = new Select("UI开发", "RecyclerView", new RecyclerActivity());
        list.add(select);
        Select select2 = new Select("UI开发", "RecyclerView实现简单的聊天界面", new RecyclerTestActivity());
        list.add(select2);
    }

    private void init2() {
        Select select = new Select("Fragment", "碎片的简单使用", new FragmentTest1Activity());
        list.add(select);
        Select select2 = new Select("Fragment", "实战：一个简易版的新闻应用", new NewsActivity());
        list.add(select2);
    }

    private void init3() {
        Select select = new Select("全剧大喇叭-广播", "动态注册-监听网络变化的广播", new BroadcastTestActivity());
        list.add(select);
        Select select1 = new Select("全剧大喇叭-广播", "发送自定义的广播", new BroadcastMyActivity());
        list.add(select1);
    }

    private void init4() {
        Select select = new Select("数据存储（持久化）", "文件存储", new FileTest1Activity());
        list.add(select);
        Select select2 = new Select("数据存储（持久化）", "SharedPreference存储", new SharedTest2Activity());
        list.add(select2);
    }
}
