package com.fw.test.test.mockito;

/**
 * description :
 * author : apple
 * date : 2021/9/18 10:40
 */
public class Home {
    private Person person;

    public Home(Person person) {
        this.person = person;
    }

    public String getMaster() {
        return person.getName();
    }
}
