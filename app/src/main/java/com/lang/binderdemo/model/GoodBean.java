package com.lang.binderdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodBean implements Parcelable {
    private String goodName;
    private String id;

    public static GoodBean fromParecl(Parcel in){
        GoodBean goodBean=new GoodBean();
        goodBean.goodName=in.readString();
        goodBean.id=in.readString();
        return goodBean;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goodName);
        dest.writeString(id);
    }
    public static final Creator<GoodBean> CREATOR=new Creator<GoodBean>() {
        @Override
        public GoodBean createFromParcel(Parcel source) {
            return fromParecl(source);
        }

        @Override
        public GoodBean[] newArray(int size) {
            return new GoodBean[size];
        }
    };
}
