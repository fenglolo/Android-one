package com.fw.androidone.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.activity.broadcast.BroadcastMyActivity;
import com.fw.androidone.activity.broadcast.BroadcastTestActivity;
import com.fw.androidone.activity.contentprovider.ContentProviderGetLocalPhoneNumActivity;
import com.fw.androidone.activity.contentprovider.ContentProviderTextActivity;
import com.fw.androidone.activity.designview.activity.DesignViewActivity1;
import com.fw.androidone.activity.designview.activity.DesignViewActivity2;
import com.fw.androidone.activity.designview.activity.DesignViewActivity3;
import com.fw.androidone.activity.filepersistenec.DbActivity;
import com.fw.androidone.activity.filepersistenec.DbLitepalActivity;
import com.fw.androidone.activity.filepersistenec.FileTest1Activity;
import com.fw.androidone.activity.filepersistenec.SharedTest2Activity;
import com.fw.androidone.activity.fragment.FragmentTest1Activity;
import com.fw.androidone.activity.fragment.NewsActivity;
import com.fw.androidone.activity.login.LoginActivity;
import com.fw.androidone.activity.login.LoginOutTestActivity;
import com.fw.androidone.activity.material.MaterialTestActivity;
import com.fw.androidone.activity.network.HttpUrlConnectionActivity;
import com.fw.androidone.activity.network.OkHttpActivity;
import com.fw.androidone.activity.notification.NotificationActivity;
import com.fw.androidone.activity.paly.PlayAudioActivity;
import com.fw.androidone.activity.paly.PlayVideoActivity;
import com.fw.androidone.activity.permission.PermissonActivity;
import com.fw.androidone.activity.photo.OpenCameraActivity;
import com.fw.androidone.activity.photo.TakePhotoActivity;
import com.fw.androidone.activity.recycler.RecyclerActivity;
import com.fw.androidone.activity.recycler.RecyclerTestActivity;
import com.fw.androidone.activity.scan.ScanActivity;
import com.fw.androidone.activity.service.ServiceTestActivity;
import com.fw.androidone.activity.thread.AndroidThreadActivity;
import com.fw.androidone.activity.webview.WebviewActivity;
import com.fw.androidone.adapter.SelectAdapter;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.Select;
import com.fw.androidone.weather.WeatherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/8 13:39
 */
public class SelectActivity extends BaseActivity {
    public static final int CAMERA_REQ_CODE = 0x01;

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
        } else if (type == 9) {
            init9();
        } else if (type == 10) {
            init10();
        } else if (type == 11) {
            init11();
        } else if (type == 12) {
            init12();
        } else if (type == 13) {
            init13();
        } else if (type == 14) {
            init14();
        } else if (type == 15) {
            init15();
        }else if (type == 16) {
            init16();
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
                if (select.getActivity().getClass().equals(ScanActivity.class)) {
                    //CAMERA_REQ_CODE为用户自定义，用于接收权限校验结果
                    ActivityCompat.requestPermissions(SelectActivity.this, new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_REQ_CODE);
                } else {
                    startActivity(new Intent(SelectActivity.this, select.getActivity().getClass()));
                }
            }
        });
        //使用Lambda表达式
        adapter.setItemClickListener(position -> {
            Select select = list.get(position);
            startActivity(new Intent(SelectActivity.this, select.getActivity().getClass()));
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

    private void init9() {
        Select select = new Select("调用摄像头和相册", "调用摄像头", new TakePhotoActivity());
        list.add(select);
        Select select2 = new Select("调用摄像头和相册", "打开相册", new OpenCameraActivity());
        list.add(select2);
    }

    private void init10() {
        Select select = new Select("播放多媒体文件", "播放音频", new PlayAudioActivity());
        list.add(select);
        Select select2 = new Select("播放多媒体文件", "播放视频", new PlayVideoActivity());
        list.add(select2);
    }

    private void init11() {
        Select select = new Select("使用网络技术", "webview", new WebviewActivity());
        list.add(select);
        Select select2 = new Select("使用网络技术", "HttpUrlConnection", new HttpUrlConnectionActivity());
        list.add(select2);
        Select select3 = new Select("使用网络技术", "OkHttp", new OkHttpActivity());
        list.add(select3);
    }

    private void init12() {
        Select select = new Select("服务", "在子线程中更新UI", new AndroidThreadActivity());
        list.add(select);
        Select select2 = new Select("服务", "开启、关闭、绑定、解绑服务", new ServiceTestActivity());
        list.add(select2);
    }

    private void init13() {
        Select select = new Select("UI-Material Design", "Toolbar", new MaterialTestActivity());
        list.add(select);
    }

    private void init14() {
        Select select = new Select("天气查询demo", "天气", new WeatherActivity());
        list.add(select);
    }

    private void init15() {
        Select select = new Select("华为Scan扫码", "扫一扫", new ScanActivity());
        list.add(select);
    }

    private void init16() {
        Select select = new Select("自定义View", "自定义View学习(一)——准备", new DesignViewActivity1());
        list.add(select);
        Select select2 = new Select("自定义View", "自定义View学习(二)——开始了解Canvas和Paint", new DesignViewActivity2());
        list.add(select2);
        Select select3 = new Select("自定义View", "自定义View学习(三)——Paint 绘制文字属性", new DesignViewActivity3());
        list.add(select3);
    }

    //实现“onRequestPermissionsResult”函数接收校验权限结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //判断“requestCode”是否为申请权限时设置请求码CAMERA_REQ_CODE，然后校验权限开启状态
        if (requestCode == CAMERA_REQ_CODE && grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //调用扫码接口，构建扫码能力，需您实现
            startActivity(new Intent(SelectActivity.this, ScanActivity.class));
        } else {
            Toast.makeText(SelectActivity.this, "未授权", Toast.LENGTH_SHORT).show();
        }
    }
}
