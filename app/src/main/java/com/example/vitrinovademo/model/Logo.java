package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

public  class Logo  implements Parcelable {

    private Medium medium;
    private Cover.Thumbnail thumbnail;

    public Cover.Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Cover.Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    private Logo(Parcel in) {
        medium = in.readParcelable(Medium.class.getClassLoader());
        thumbnail = in.readParcelable(Cover.Thumbnail.class.getClassLoader());
    }

    public static final Creator<Logo> CREATOR = new Creator<Logo>() {
        @Override
        public Logo createFromParcel(Parcel in) {
            return new Logo(in);
        }

        @Override
        public Logo[] newArray(int size) {
            return new Logo[size];
        }
    };

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(medium,flags);
        dest.writeParcelable(thumbnail,flags);
    }

    public static  class Medium implements Parcelable{
        private String url;

        private Medium(Parcel in) {
            url = in.readString();
        }

        public static  final Creator<Medium> CREATOR = new Creator<Medium>() {
            @Override
            public Medium createFromParcel(Parcel in) {
                return new Medium(in);
            }

            @Override
            public Medium[] newArray(int size) {
                return new Medium[size];
            }
        };

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
        }
    }
}
