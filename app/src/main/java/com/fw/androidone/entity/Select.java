package com.fw.androidone.entity;

import android.app.Activity;

/**
 * description :
 * author : apple
 * date : 2021/3/8 13:57
 */
public class Select {
    private String type;
    private String text;
    private Activity activity;

    public Select(String type, String text, Activity activity) {
        this.type = type;
        this.text = text;
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
