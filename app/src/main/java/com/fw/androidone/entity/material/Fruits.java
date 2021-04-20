package com.fw.androidone.entity.material;

/**
 * description :Fruit实体类
 * author : apple
 * date : 2021/4/19 16:48
 */
public class Fruits {
    private String name;
    private int imgId;

    public Fruits(String name, int resource) {
        this.name = name;
        this.imgId = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
