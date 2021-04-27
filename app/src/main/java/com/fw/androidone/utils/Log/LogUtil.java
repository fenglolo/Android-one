package com.fw.androidone.utils.Log;

import android.util.Log;

/**
 * description :Log日志工具类:控制打印的日志类型，当level=6，则屏蔽所有日志。
 * author : apple
 * date : 2021/4/27 14:20
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int level = VERBOSE;

    //打印 VERBOSE 级别的日志
    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    //打印 DEBUG 级别的日志
    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    //打印 INFO 级别的日志
    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    //打印 WARN 级别的日志
    public static void w(String tag, String msg) {
        if (level <= WARN) {
            Log.w(tag, msg);
        }
    }

    //打印 ERROR 级别的日志
    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }

}
