package com.arshad.mindvalley.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Parcelable {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("download")
    @Expose
    private String download;

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

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    protected Links(Parcel in) {
        self = in.readString();
        html = in.readString();
        download = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(self);
        dest.writeString(html);
        dest.writeString(download);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

}