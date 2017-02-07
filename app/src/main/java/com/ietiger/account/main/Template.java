package com.ietiger.account.main;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : tiger
 * email  : liuxh@lovewith.me
 * time   : 16-11-24 上午11:50
 */
public class Template implements Parcelable {

    private String name;
    private int goods_id;
    private float price;
    private String bucket_domain;
    private String path;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBucket_domain() {
        return bucket_domain;
    }

    public void setBucket_domain(String bucket_domain) {
        this.bucket_domain = bucket_domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.goods_id);
        dest.writeFloat(this.price);
        dest.writeString(this.bucket_domain);
        dest.writeString(this.path);
        dest.writeInt(this.id);
    }

    public Template() {
    }

    protected Template(Parcel in) {
        this.name = in.readString();
        this.goods_id = in.readInt();
        this.price = in.readFloat();
        this.bucket_domain = in.readString();
        this.path = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<Template> CREATOR = new Creator<Template>() {
        @Override
        public Template createFromParcel(Parcel source) {
            return new Template(source);
        }

        @Override
        public Template[] newArray(int size) {
            return new Template[size];
        }
    };
}
