package com.cloudtv.hahong.aidlparcelable;

/**
 * Created by hahong on 15-10-23.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Person implements Parcelable {
    private Integer id;
    private String name;

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        Log.v("writeToParcel",this.name);
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel dest) {
        id=dest.readInt();
        name=dest.readString();
        Log.v("readFromParcel",name);

    }

    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>()

    {
        @Override
        public Person createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            return new Person(source.readInt(), source.readString());
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
