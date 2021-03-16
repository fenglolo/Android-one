package com.fw.androidone.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fw.androidone.MainActivity;
import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;

/**
 * description :
 * author : apple
 * date : 2021/3/16 09:31
 */
public class LoginActivity extends BaseActivity {
    private EditText et_name;
    private EditText et_pwd;
    private Button btn_login;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_name = findViewById(R.id.et_login_name);
        et_pwd = findViewById(R.id.et_login_pwd);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String pwd = et_pwd.getText().toString();
                if (TextUtils.equals(name, "admin") && TextUtils.equals(pwd, "123456")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "账号密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
