package com.example.vitrinovademo.model;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResponseModel {

    private String type;
    private String title;
    private List<Featured> featured= new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Collection> collections = new ArrayList<>();
    private List<Shops> shops = new ArrayList<>();

    public List<Shops> getShops() {
        return shops;
    }

    public void setShops(List<Shops> shops) {
        this.shops = shops;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Featured> getFeatureds() {
        return featured;
    }

    public void setFeatureds(List<Featured> featureds) {
        this.featured = featureds;
    }
}
