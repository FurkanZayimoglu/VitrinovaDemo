package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    private String title;
    private int old_price;
    private int price;
    private Shop shop;
    private List<PopularProduct.ImagePopular> images= new ArrayList<>();

    public List<PopularProduct.ImagePopular> getImages() {
        return images;
    }

    public void setImages(List<PopularProduct.ImagePopular> images) {
        this.images = images;
    }

    private Product(Parcel in) {
        title = in.readString();
        old_price = in.readInt();
        price = in.readInt();
        shop =  in.readParcelable(Shop.class.getClassLoader());
        in.readList(images, PopularProduct.ImagePopular.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(old_price);
        dest.writeInt(price);
        dest.writeParcelable(shop, flags);
        dest.writeList(images);
    }

}
