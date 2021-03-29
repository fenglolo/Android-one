package com.fw.androidone.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.activity.broadcast.BroadcastMyActivity;
import com.fw.androidone.activity.broadcast.BroadcastTestActivity;
import com.fw.androidone.activity.contentprovider.ContentProviderGetLocalPhoneNumActivity;
import com.fw.androidone.activity.contentprovider.ContentProviderTextActivity;
import com.fw.androidone.activity.filepersistenec.DbActivity;
import com.fw.androidone.activity.filepersistenec.DbLitepalActivity;
import com.fw.androidone.activity.filepersistenec.FileTest1Activity;
import com.fw.androidone.activity.filepersistenec.SharedTest2Activity;
import com.fw.androidone.activity.fragment.FragmentTest1Activity;
import com.fw.androidone.activity.fragment.NewsActivity;
import com.fw.androidone.activity.login.LoginActivity;
import com.fw.androidone.activity.login.LoginOutTestActivity;
import com.fw.androidone.activity.notification.NotificationActivity;
import com.fw.androidone.activity.permission.PermissonActivity;
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
        } else if (type == 5) {
            init5();
        } else if (type == 6) {
            init6();
        } else if (type == 7) {
            init7();
        } else if (type == 8) {
            init8();
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
        Select select3 = new Select("数据存储（持久化）", "sqlite数据库操作", new DbActivity());
        list.add(select3);
        Select select4 = new Select("数据存储（持久化）", "litepal数据库操作", new DbLitepalActivity());
        list.add(select4);
    }

    private void init5() {
        Select select = new Select("登录", "登录页面", new LoginActivity());
        list.add(select);
        Select select2 = new Select("登录", "测试强制下线（广播）", new LoginOutTestActivity());
        list.add(select2);
    }

    private void init6() {
        Select select = new Select("动态权限申请", "动态申请打电话权限", new PermissonActivity());
        list.add(select);
    }

    private void init7() {
        Select select = new Select("内容提供器content provider", "getContentResolver从content provider中获取数据", new ContentProviderTextActivity());
        list.add(select);
        Select select2 = new Select("内容提供器content provider", "获取手机联系人", new ContentProviderGetLocalPhoneNumActivity());
        list.add(select2);
    }

    private void init8() {
        Select select = new Select("通知Notification", "测试通知", new NotificationActivity());
        list.add(select);
    }
}
