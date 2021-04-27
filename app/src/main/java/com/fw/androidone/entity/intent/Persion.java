package com.fw.androidone.entity.intent;

import java.io.Serializable;

/**
 * description :
 * author : apple
 * date : 2021/4/27 11:07
 */
public class Persion implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
