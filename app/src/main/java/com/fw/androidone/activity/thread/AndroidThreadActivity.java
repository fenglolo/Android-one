package com.fw.androidone.activity.thread;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;


/**
 * description :(1)android 子线程、(2)异步消息处理机制:Handler和AsyncTask
 * author : apple
 * date : 2021/4/12 09:58
 */
public class AndroidThreadActivity extends BaseActivity {
    private Button button;
    private TextView textView;

    public static final int UPDATE_TEXT = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //在这里进行UI操作
                    textView.setText("这是更新后的UI");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getContentLayout() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.btn_thread_change);
        textView = findViewById(R.id.tv_thread_text);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟在子线程中操作完成后，需要进行UI更新；
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void initData() {

        //新建子线程
        newThread();
        //开启AsyncTask()
        dialog = new ProgressDialog(this);
        new DownloadTask().execute();
    }

    private void newThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    ProgressDialog dialog;

    class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

        //进行一些初始化操作
        @Override
        protected void onPreExecute() {
            //显示对话框操作
            dialog.show();
        }

        //该方法在子线程中执行，进行一些耗时操作
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                while (true) {
                    //在这里进行下载操作
                    int downloadPercent = 100;//这里应该是一个下载方法返回的下载实时进度；
                    publishProgress(downloadPercent);//使用publishProgress方法，把实时下载进度发送到onProgressUpdate方法；
                    if (downloadPercent >= 100) {
                        break;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        // 当后台任务调用了publishProgress()方法后，该方法会被调用，参数是从后台任务传递过来的。
        // 该方法中进行UI操作
        @Override
        protected void onProgressUpdate(Integer... values) {
            //在这里更新下载进度
            dialog.setMessage("下载" + values[0] + "%");
        }

        //后台方法执行完毕通过return语句返回时，该方法很快会被调用，可以利用返回的参数进行UI操作。o
        @Override
        protected void onPostExecute(Boolean result) {
            //关闭对话框操作
            dialog.dismiss();
            //在这里提示下载结果
            if (result) {
                Toast.makeText(AndroidThreadActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AndroidThreadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
