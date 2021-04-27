package com.fw.androidone.activity.test;

import android.content.Intent;

import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.intent.Persion;
import com.fw.androidone.entity.intent.PersionNew;

/**
 * description :
 * author : apple
 * date : 2021/4/27 11:09
 */
public class Test1Activity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        //普通方式
        Intent intent = new Intent(Test1Activity.this, Test2Activity.class);
        intent.putExtra("string_data", "123");
        intent.putExtra("int_data", 1234);
        startActivity(intent);

        //Serializable方式
        Persion persion = new Persion();
        persion.setAge(5);
        persion.setName("lihong");
        Intent intent1 = new Intent(Test1Activity.this, Test2Activity.class);
        intent1.putExtra("person_data", persion);
        startActivity(intent1);

        //Parcelable方式
        PersionNew persionNew = new PersionNew();
        persionNew.setName("lihong");
        persionNew.setAge(5);
        Intent intent2 = new Intent(Test1Activity.this, Test2Activity.class);
        intent1.putExtra("person_new_data", persionNew);
        startActivity(intent2);
    }
}
