package com.fw.androidone.sqlitelitepal.entity;

import org.litepal.crud.LitePalSupport;

/**
 * description :Book表
 * author : apple
 * date : 2021/3/19 13:59
 */
//老版本继承DataSupport,新版本继承LitePalSupport
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int page;
    private String name;

    private String press;//新增字段，需要提升数据库版本

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
