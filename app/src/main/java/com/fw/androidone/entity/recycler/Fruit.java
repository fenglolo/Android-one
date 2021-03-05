package com.fw.androidone.entity.recycler;

/**
 * description :Fruit实体类
 * author : apple
 * date : 2021/3/4 16:48
 */
public class Fruit {
    private String fruitName;
    private int fruitResource;

    public Fruit(String name, int resource) {
        this.fruitName = name;
        this.fruitResource = resource;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getFruitResource() {
        return fruitResource;
    }

    public void setFruitResource(int fruitResource) {
        this.fruitResource = fruitResource;
    }
}
