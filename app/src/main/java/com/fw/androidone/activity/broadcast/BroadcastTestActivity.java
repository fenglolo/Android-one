package com.fw.androidone.activity.broadcast;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :
 * author : apple
 * date : 2021/3/11 09:29
 */
public class BroadcastTestActivity extends BaseActivity {

    private NetWorkBroadcastReceiver netWorkBroadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_broadcast;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        netWorkBroadcastReceiver = new NetWorkBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //注册广播
        registerReceiver(netWorkBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册广播
        unregisterReceiver(netWorkBroadcastReceiver);
    }

    class NetWorkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(BroadcastTestActivity.this, "has net", Toast.LENGTH_SHORT).show();
//            @SuppressLint("ServiceCast") ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if (null != networkInfo && networkInfo.isAvailable()) {
//                Toast.makeText(BroadcastTestActivity.this, "has net", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(BroadcastTestActivity.this, "no net", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
