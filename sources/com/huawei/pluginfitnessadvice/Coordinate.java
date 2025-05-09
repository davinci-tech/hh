package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class Coordinate implements Parcelable {
    public static final Parcelable.Creator<Coordinate> CREATOR = new Parcelable.Creator<Coordinate>() { // from class: com.huawei.pluginfitnessadvice.Coordinate.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clM_, reason: merged with bridge method [inline-methods] */
        public Coordinate createFromParcel(Parcel parcel) {
            return new Coordinate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Coordinate[] newArray(int i) {
            return new Coordinate[i];
        }
    };
    private static final String TAG = "Coordinate";

    @SerializedName("x")
    private int mCoordinateX;

    @SerializedName("y")
    private int mCoordinateY;

    @SerializedName("id")
    private String mId;

    @SerializedName("tip")
    private String mTip;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Coordinate() {
    }

    protected Coordinate(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Coordinate in == null");
            return;
        }
        this.mId = parcel.readString();
        this.mCoordinateX = parcel.readInt();
        this.mCoordinateY = parcel.readInt();
        this.mTip = parcel.readString();
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public int getX() {
        return this.mCoordinateX;
    }

    public void setX(int i) {
        this.mCoordinateX = i;
    }

    public int getY() {
        return this.mCoordinateY;
    }

    public void setY(int i) {
        this.mCoordinateY = i;
    }

    public String getTip() {
        return this.mTip;
    }

    public void setTip(String str) {
        this.mTip = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeInt(this.mCoordinateX);
        parcel.writeInt(this.mCoordinateY);
        parcel.writeString(this.mTip);
    }
}
