package com.arshad.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLinks implements Parcelable {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("photos")
    @Expose
    private String photos;
    @SerializedName("likes")
    @Expose
    private String likes;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    protected UserLinks(Parcel in) {
        self = in.readString();
        html = in.readString();
        photos = in.readString();
        likes = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(self);
        dest.writeString(html);
        dest.writeString(photos);
        dest.writeString(likes);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserLinks> CREATOR = new Parcelable.Creator<UserLinks>() {
        @Override
        public UserLinks createFromParcel(Parcel in) {
            return new UserLinks(in);
        }

        @Override
        public UserLinks[] newArray(int size) {
            return new UserLinks[size];
        }
    };

}