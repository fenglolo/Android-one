package com.fw.androidone.activity.test;

import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.intent.Persion;
import com.fw.androidone.entity.intent.PersionNew;

/**
 * description :
 * author : apple
 * date : 2021/4/27 11:09
 */
public class Test2Activity extends BaseActivity {
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
        String value = getIntent().getStringExtra("string_data");
        int i = getIntent().getIntExtra("int_data", 0);

        //Serializable方式
        Persion persion = (Persion) getIntent().getSerializableExtra("person_data");

        //Parcelable方式
        PersionNew persionNew = getIntent().getParcelableExtra("person_new_data");
    }
}
