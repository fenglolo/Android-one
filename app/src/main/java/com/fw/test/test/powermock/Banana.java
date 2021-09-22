package com.fw.test.test.powermock;

/**
 * description :
 * author : apple
 * date : 2021/9/18 14:25
 */
public class Banana extends Fruit {

    private static String COLOR = "黄色的";

    public Banana() {

    }

    public static String getColor() {
        return COLOR;
    }

    public String getBananaInfo() {
        return flavor() + getColor();
    }

    private String flavor() {
        return "甜甜的";
    }

    public final boolean isLike() {
        return true;
    }
}
