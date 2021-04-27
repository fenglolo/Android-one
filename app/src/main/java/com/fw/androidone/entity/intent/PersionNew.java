package com.fw.androidone.entity.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description :
 * author : apple
 * date : 2021/4/27 11:07
 */
public class PersionNew implements Parcelable {
    private String name;
    private int age;

    public static final Creator<PersionNew> CREATOR = new Creator<PersionNew>() {
        @Override
        public PersionNew createFromParcel(Parcel in) {
            PersionNew persionNew = new PersionNew();
            persionNew.name = in.readString();//读出name
            persionNew.age = in.readInt();//读出age
            return persionNew;
        }

        @Override
        public PersionNew[] newArray(int size) {
            return new PersionNew[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);//写出name
        dest.writeInt(age);//写出age
    }

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
