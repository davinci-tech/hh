package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;

/* loaded from: classes6.dex */
public class RoadBook implements Parcelable {
    public static final Parcelable.Creator<RoadBook> CREATOR = new Parcelable.Creator<RoadBook>() { // from class: com.huawei.pluginfitnessadvice.RoadBook.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmb_, reason: merged with bridge method [inline-methods] */
        public RoadBook createFromParcel(Parcel parcel) {
            return new RoadBook(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RoadBook[] newArray(int i) {
            return new RoadBook[i];
        }
    };

    @SerializedName("ascend")
    private int mAscend;

    @SerializedName(TypedValues.Custom.S_DIMENSION)
    private double mDimension;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("elevation")
    private int mElevation;

    @SerializedName(JsbMapKeyNames.H5_LOC_LON)
    private double mLongitude;

    @SerializedName("mileage")
    private int mMileage;

    @SerializedName("slope")
    private int mSlope;

    @SerializedName("speed")
    private int mSpeed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private RoadBook(Parcel parcel) {
        this.mDuration = parcel.readInt();
        this.mAscend = parcel.readInt();
        this.mMileage = parcel.readInt();
        this.mSlope = parcel.readInt();
        this.mSpeed = parcel.readInt();
        this.mElevation = parcel.readInt();
        this.mLongitude = parcel.readDouble();
        this.mDimension = parcel.readDouble();
    }

    public RoadBook() {
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public int getAscend() {
        return this.mAscend;
    }

    public int getMileage() {
        return this.mMileage;
    }

    public void setMileage(int i) {
        this.mMileage = i;
    }

    public int getSlope() {
        return this.mSlope;
    }

    public void setSlope(int i) {
        this.mSlope = i;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
    }

    public int getElevation() {
        return this.mElevation;
    }

    public void setElevation(int i) {
        this.mElevation = i;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    public double getDimension() {
        return this.mDimension;
    }

    public void setDimension(double d) {
        this.mDimension = d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mAscend);
        parcel.writeInt(this.mMileage);
        parcel.writeInt(this.mSlope);
        parcel.writeInt(this.mSpeed);
        parcel.writeInt(this.mElevation);
        parcel.writeDouble(this.mLongitude);
        parcel.writeDouble(this.mDimension);
    }
}
