package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Shops implements Parcelable {

    private String name;
    private String definition;
    private List<PopularProduct> popular_products = new ArrayList<>();
    private int product_count;
    private Cover cover;
    private Logo logo;

    private Shops(Parcel in) {
        name = in.readString();
        definition = in.readString();
        in.readList(popular_products,PopularProduct.class.getClassLoader());
        product_count =in.readInt();
        cover = in.readParcelable(Cover.class.getClassLoader());
        logo = in.readParcelable(Logo.class.getClassLoader());
    }

    public static final Creator<Shops> CREATOR = new Creator<Shops>() {
        @Override
        public Shops createFromParcel(Parcel in) {
            return new Shops(in);
        }

        @Override
        public Shops[] newArray(int size) {
            return new Shops[size];
        }
    };

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<PopularProduct> getPopular_products() {
        return popular_products;
    }

    public void setPopular_products(List<PopularProduct> popular_products) {
        this.popular_products = popular_products;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(definition);
        dest.writeList(popular_products);
        dest.writeInt(product_count);
        dest.writeParcelable(cover, flags);
        dest.writeParcelable(logo, flags);
    }
}
