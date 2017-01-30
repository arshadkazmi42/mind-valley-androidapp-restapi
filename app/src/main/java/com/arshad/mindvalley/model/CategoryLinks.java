package com.arshad.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryLinks implements Parcelable {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("photos")
    @Expose
    private String photos;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    protected CategoryLinks(Parcel in) {
        self = in.readString();
        photos = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(self);
        dest.writeString(photos);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CategoryLinks> CREATOR = new Parcelable.Creator<CategoryLinks>() {
        @Override
        public CategoryLinks createFromParcel(Parcel in) {
            return new CategoryLinks(in);
        }

        @Override
        public CategoryLinks[] newArray(int size) {
            return new CategoryLinks[size];
        }
    };

}