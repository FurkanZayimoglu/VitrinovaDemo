package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Collection implements Parcelable {

    private String title;
    private String definition;
    private Cover cover;

    protected Collection(Parcel in) {
        title = in.readString();
        definition = in.readString();
        cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(definition);
        dest.writeParcelable(cover,flags);
    }
}
