package com.fw.androidone.activity.filepersistenec;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * description :文件存储测试类/文件存储和文件读取
 * author : apple
 * date : 2021/3/15 13:50
 */
public class FileTest1Activity extends BaseActivity {
    private EditText editText;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_file_test_one;
    }

    @Override
    protected void initView() {
        editText = findViewById(R.id.ed_file_test);
    }

    @Override
    protected void initAction() {
        String msg = load();
        if (!TextUtils.isEmpty(msg)) {
            editText.setText(msg);
            editText.setSelection(msg.length());
            Toast.makeText(this, "读取文件成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(editText.getText().toString());
    }

    //文件存储
    private void save(String msg) {
        FileOutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedWriter) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //文件读取
    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
