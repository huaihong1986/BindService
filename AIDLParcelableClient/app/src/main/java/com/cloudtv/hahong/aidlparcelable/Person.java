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
        Log.v("writeToParcelClient",this.name);
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }
    /*
     void save(inout Person person);
     *对于非基本数据类型和String和CharSequence类型,要加上方向指示
	 *包括in、out和inout，in表示由客户端设置，out表示由服务端设置，inout是两者均可设置。
	 * in 需要实现public void writeToParcel(Parcel dest, int flags)
	 * out 需要实现public void readFromParcel(Parcel dest)
     */
    public void readFromParcel(Parcel dest) {
        id=dest.readInt();
        name=dest.readString();
        Log.v("readFromParcelClient",name);

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

