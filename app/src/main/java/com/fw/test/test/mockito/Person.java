package com.fw.test.test.mockito;

/**
 * description :Person类
 * author : apple
 * date : 2021/9/17 15:25
 */
public class Person {

    private String name;
    private int sex;
    private int age;

    public String eat(String food) {
        return food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return 11;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
