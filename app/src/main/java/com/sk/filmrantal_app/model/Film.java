package com.sk.filmrantal_app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Film implements Parcelable {
    private String title;
    private String visitor;
    private @DrawableRes int moreIcon;

    public Film(String title, String visitor,@DrawableRes int moreIcon) {
        this.title = title;
        this.visitor = visitor;
        this.moreIcon=moreIcon;
    }

    public int getMoreIcon() {
        return moreIcon;
    }

    public void setMoreIcon(int moreIcon) {
        this.moreIcon = moreIcon;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    @Override
    public int describeContents() {
        return 0;
    }
 protected Film(Parcel in){
        title=in.readString();
        visitor=in.readString();
        moreIcon=in.readInt();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(visitor);
    dest.writeInt(moreIcon);
    }
}
