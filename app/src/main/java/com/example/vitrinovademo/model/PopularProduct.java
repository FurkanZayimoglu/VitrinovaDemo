package com.example.vitrinovademo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PopularProduct implements Parcelable {

    private List<ImagePopular> images= new ArrayList<>();

    private PopularProduct(Parcel in) {
         in.readList(images,ImagePopular.class.getClassLoader());
    }

    public static final Creator<PopularProduct> CREATOR = new Creator<PopularProduct>() {
        @Override
        public PopularProduct createFromParcel(Parcel in) {
            return new PopularProduct(in);
        }

        @Override
        public PopularProduct[] newArray(int size) {
            return new PopularProduct[size];
        }
    };

    public List<ImagePopular> getImages() {
        return images;
    }

    public void setImages(List<ImagePopular> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(images);
    }
//-------------------------------------------------------------------------------------------
    public static class ImagePopular implements Parcelable {
        private ChooseThumbnail thumbnail;

        private ImagePopular(Parcel in) {
            thumbnail = in.readParcelable(ChooseThumbnail.class.getClassLoader());
        }

        public final static Creator<ImagePopular> CREATOR = new Creator<ImagePopular>() {
            @Override
            public ImagePopular createFromParcel(Parcel in) {
                return new ImagePopular(in);
            }

            @Override
            public ImagePopular[] newArray(int size) {
                return new ImagePopular[size];
            }
        };

        public ChooseThumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(ChooseThumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(thumbnail, flags);
        }
//-----------------------------------------------------------------------------------------------------
        public static class ChooseThumbnail implements Parcelable {
            private String url;

            private ChooseThumbnail(Parcel in) {
                url = in.readString();
            }

            public final static Creator<ChooseThumbnail> CREATOR = new Creator<ChooseThumbnail>() {
                @Override
                public ChooseThumbnail createFromParcel(Parcel in) {
                    return new ChooseThumbnail(in);
                }

                @Override
                public ChooseThumbnail[] newArray(int size) {
                    return new ChooseThumbnail[size];
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

}

