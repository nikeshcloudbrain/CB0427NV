package com.creditscore.creditscorecheck.loan.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tips implements Parcelable {
    public String title;
    public String description;

    public Tips(String title, String description) {
        this.title =title;
        this.description = description;
    }


    protected Tips(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Tips> CREATOR = new Creator<Tips>() {
        @Override
        public Tips createFromParcel(Parcel in) {
            return new Tips(in);
        }

        @Override
        public Tips[] newArray(int size) {
            return new Tips[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
    }
}
