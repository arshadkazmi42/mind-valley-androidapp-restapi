package com.arshad.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("current_user_collections")
    @Expose
    private List<Object> currentUserCollections = null;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("links")
    @Expose
    private Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    protected DataResponse(Parcel in) {
        id = in.readString();
        createdAt = in.readString();
        width = in.readByte() == 0x00 ? null : in.readInt();
        height = in.readByte() == 0x00 ? null : in.readInt();
        color = in.readString();
        likes = in.readByte() == 0x00 ? null : in.readInt();
        byte likedByUserVal = in.readByte();
        likedByUser = likedByUserVal == 0x02 ? null : likedByUserVal != 0x00;
        user = (User) in.readValue(User.class.getClassLoader());
        if (in.readByte() == 0x01) {
            currentUserCollections = new ArrayList<Object>();
            in.readList(currentUserCollections, Object.class.getClassLoader());
        } else {
            currentUserCollections = null;
        }
        urls = (Urls) in.readValue(Urls.class.getClassLoader());
        if (in.readByte() == 0x01) {
            categories = new ArrayList<Category>();
            in.readList(categories, Category.class.getClassLoader());
        } else {
            categories = null;
        }
        links = (Links) in.readValue(Links.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(createdAt);
        if (width == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(width);
        }
        if (height == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(height);
        }
        dest.writeString(color);
        if (likes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(likes);
        }
        if (likedByUser == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (likedByUser ? 0x01 : 0x00));
        }
        dest.writeValue(user);
        if (currentUserCollections == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(currentUserCollections);
        }
        dest.writeValue(urls);
        if (categories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(categories);
        }
        dest.writeValue(links);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DataResponse> CREATOR = new Parcelable.Creator<DataResponse>() {
        @Override
        public DataResponse createFromParcel(Parcel in) {
            return new DataResponse(in);
        }

        @Override
        public DataResponse[] newArray(int size) {
            return new DataResponse[size];
        }
    };

}