package com.fw.androidone.activity.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :ContentProvider的测试类
 * author : apple
 * date : 2021/3/25 10:33
 */
public class ContentProviderTextActivity extends BaseActivity implements View.OnClickListener {

    private Button cp_add, cp_update, cp_delete, cp_query;

    private Uri uri;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_content_provider_text;
    }

    @Override
    protected void initView() {
        cp_add = findViewById(R.id.btn_cp_add);
        cp_update = findViewById(R.id.btn_cp_update);
        cp_delete = findViewById(R.id.btn_cp_delete);
        cp_query = findViewById(R.id.btn_cp_query);
    }

    @Override
    protected void initAction() {
        cp_add.setOnClickListener(this);
        cp_update.setOnClickListener(this);
        cp_delete.setOnClickListener(this);
        cp_query.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //uri相当于内容提供器的唯一标识符。由authority和path两部分组成。
        //authority：用来区分不同的应用，一般用包名+.provider组成
        //path：用来区分不同的表，一般由表名组成
        //uri组成：content:// + 包名 + .provider + /表名
        uri = Uri.parse("content://com.fw.androidone.provider/table1");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cp_add) {//添加数据
            ContentValues values = new ContentValues();
            values.put("colum1", "text");
            values.put("column2", 1);
            getContentResolver().insert(uri, values);

        } else if (v.getId() == R.id.btn_cp_update) {//更新数据
            ContentValues values = new ContentValues();
            values.put("colum1", "");
            getContentResolver().update(uri, values,
                    "where column1 = ?",
                    new String[]{"text"});

        } else if (v.getId() == R.id.btn_cp_delete) {//删除数据
            getContentResolver().delete(uri, "where column2 = ?", new String[]{"1"});

        } else if (v.getId() == R.id.btn_cp_query) {//查询数据
            Cursor cursor = getContentResolver().query(uri, new String[]{"column1,column2"},
                    "where column1 = ? and column2 = ?",
                    new String[]{"text,1"},
                    "column1 desc");
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String column1 = cursor.getString(cursor.getColumnIndex("column1"));
                    int column2 = cursor.getInt(cursor.getColumnIndex("column2"));
                } while (cursor.moveToNext());
            }
            cursor.close();

        }
    }
}
