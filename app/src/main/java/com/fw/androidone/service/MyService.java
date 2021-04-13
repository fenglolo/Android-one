package com.fw.androidone.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.fw.androidone.R;
import com.fw.androidone.activity.service.ServiceTestActivity;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "MyService--startDownload");
        }

        public int getProgress() {
            Log.d("MyService", "MyService--getProgress");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "MyService--onCreate");

        //创建一个前台服务
//        startTopService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "MyService--onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "MyService--onDestroy");
    }

    private void startTopService() {
        Intent intent = new Intent(this, ServiceTestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        long[] vibrate = {0, 500, 1000, 1500};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") Notification.Builder notification = new Notification
                    .Builder(this)
                    .setContentTitle("这是一个前台服务的标题")//标题
                    .setContentText("这是一个前台服务的内容")//内容
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)//小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//大图标
                    .setVibrate(vibrate)//振动
                    .setContentIntent(pendingIntent)//pendingIntent
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)//设置所有效果为默认效果。。。
                    .setChannelId("1111111111")//唯一标识
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);//设置音频

            startForeground(1, notification.build());
        } else {
            @SuppressLint("WrongConstant") Notification.Builder notification = new Notification
                    .Builder(this)
                    .setContentTitle("这是一个前台服务的标题")
                    .setContentText("这是一个前台服务的内容")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setVibrate(vibrate)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);

            startForeground(1, notification.build());
        }
    }
}