package com.fw.androidone.activity.recycler;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.adapter.recycler.MsgAdapter;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.entity.recycler.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/8 08:21
 */
public class RecyclerTestActivity extends BaseActivity implements View.OnClickListener {

    private List<Msg> list = new ArrayList<>();
    private LinearLayoutManager manager;
    private MsgAdapter adapter;

    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_recycler_text;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.rv_msg);
        editText = findViewById(R.id.et_text);
        button = findViewById(R.id.btn_send);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initMsg();
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MsgAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send) {
            String text = editText.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "请输入消息", Toast.LENGTH_SHORT).show();
            } else {
                Msg msg = new Msg(text, Msg.TYPE_SENG);
                list.add(msg);
                adapter.notifyItemInserted(list.size() - 1);//刷新adapter
                recyclerView.scrollToPosition(list.size() - 1);//滚动到最后一条消息处

                editText.setText("");
            }
        }
    }

    private void initMsg() {
        Msg msg = new Msg("你好", Msg.TYPE_RECEIVED);
        list.add(msg);
        Msg msg2 = new Msg("你好呀", Msg.TYPE_SENG);
        list.add(msg2);
        Msg msg3 = new Msg("吃饭了吗", Msg.TYPE_RECEIVED);
        list.add(msg3);
    }
}
