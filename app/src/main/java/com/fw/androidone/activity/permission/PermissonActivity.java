package com.fw.androidone.activity.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :动态申请权限demo
 * author : apple
 * date : 2021/3/24 14:29
 */
public class PermissonActivity extends BaseActivity {

    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_call);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有CALL_PHONE权限
                if (ContextCompat.checkSelfPermission(PermissonActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PermissonActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(PermissonActivity.this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:1001001"));
        startActivity(intent);
    }
}
