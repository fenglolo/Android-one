package com.fw.androidone.utils;

/**
 * description :notification通知工具类
 * author : apple
 * date : 2021/3/29 13:55
 */

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.app.NotificationCompat;

import com.fw.androidone.activity.notification.NotificationtestActivity;

public class NotificationManagerUtils {

    //开始通知
    @SuppressLint("WrongConstant")
    public static void startNotificationManager(Context context, String title, String msg, int idIco) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, NotificationtestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        long[] vibrate = {0, 500, 1000, 1500};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder notification = new Notification
                    .Builder(context)
                    .setContentTitle(title)//标题
                    .setContentText(msg)//内容
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(idIco)//小图标
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), idIco))//大图标
                    .setVibrate(vibrate)//振动
                    .setContentIntent(pendingIntent)//pendingIntent
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)//设置所有效果为默认效果。。。
                    .setChannelId(getVersionName(context))//唯一标识
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);//设置音频

            NotificationChannel channel = new NotificationChannel(
                    getVersionName(context),
                    "会话消息(掌嗨)",
                    NotificationManager.IMPORTANCE_DEFAULT

            );

            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(0, notification.build());
        } else {
            Notification.Builder notification = new Notification
                    .Builder(context)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(idIco)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), idIco))
                    .setVibrate(vibrate)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
            notificationManager.notify(0, notification.build());
        }

    }

    /**
     * versionName
     *
     * @param context 上下文
     * @return 当前应用的版本名称
     */
    public static synchronized String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return packageInfo.versionName;
        } else {
            return null;
        }
    }

    public static synchronized PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

