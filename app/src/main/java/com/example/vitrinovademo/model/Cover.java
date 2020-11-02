package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cover implements Parcelable {

    private String url;
    private Medium medium;
    private Thumbnail thumbnail;

    private Cover(Parcel in) {
        url = in.readString();
        medium = in.readParcelable(Medium.class.getClassLoader());
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeParcelable(medium,flags);
        dest.writeParcelable(thumbnail,flags);
    }

    public static class Medium implements Parcelable{
       private   String url;

        private Medium(Parcel in) {
            url = in.readString();
        }

        public static final Creator<Medium> CREATOR = new Creator<Medium>() {
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

    public static class Thumbnail implements Parcelable {
        private String url;

        private Thumbnail(Parcel in) {
            url = in.readString();
        }

        public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
            @Override
            public Thumbnail createFromParcel(Parcel in) {
                return new Thumbnail(in);
            }

            @Override
            public Thumbnail[] newArray(int size) {
                return new Thumbnail[size];
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
