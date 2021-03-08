package com.fw.androidone.entity.recycler;

/**
 * description :聊天测试类
 * author : apple
 * date : 2021/3/8 08:21
 */
public class Msg {
    public static final int TYPE_SENG = 1;
    public static final int TYPE_RECEIVED = 2;
    private String context;
    private int type;//消息类型：1发送 2接收

    public Msg(String context, int type) {
        this.context = context;
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
