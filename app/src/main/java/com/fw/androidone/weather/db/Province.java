package com.fw.androidone.weather.db;

import org.litepal.crud.LitePalSupport;

/**
 * description :
 * author : apple
 * date : 2021/4/28 10:55
 */
//老版本继承DataSupport,新版本继承LitePalSupport
public class Province extends LitePalSupport {
    private int id;
    private String provinceName;
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
