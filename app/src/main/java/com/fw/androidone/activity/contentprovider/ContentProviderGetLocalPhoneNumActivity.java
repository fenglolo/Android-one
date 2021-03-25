package com.fw.androidone.activity.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * description :ContentProvider的测试类:获取本地手机联系人和手机号
 * author : apple
 * date : 2021/3/25 10:33
 */
public class ContentProviderGetLocalPhoneNumActivity extends BaseActivity {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_content_provider_get_local_phonenum;
    }

    @Override
    protected void initView() {
        listView = findViewById(R.id.lv_local_phonenum);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContects();
        }
    }

    private void readContects() {
        //获取手机联系人
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                list.add(name + "\n" + number);
            }
            arrayAdapter.notifyDataSetChanged();
        }
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContects();
                } else {
                    Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
}
