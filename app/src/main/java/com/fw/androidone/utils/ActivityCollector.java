package com.fw.androidone.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * description :activity管理类
 * author : apple
 * date : 2021/3/16 09:39
 */
public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
