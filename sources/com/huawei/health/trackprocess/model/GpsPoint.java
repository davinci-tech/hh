package com.huawei.health.trackprocess.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class GpsPoint implements Serializable, Parcelable {
    public static final Parcelable.Creator<GpsPoint> CREATOR = new Parcelable.Creator<GpsPoint>() { // from class: com.huawei.health.trackprocess.model.GpsPoint.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: aNp_, reason: merged with bridge method [inline-methods] */
        public GpsPoint createFromParcel(Parcel parcel) {
            return new GpsPoint(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public GpsPoint[] newArray(int i) {
            return new GpsPoint[i];
        }
    };

    @SerializedName("altitude")
    private double altitude;

    @SerializedName("cumulateDis")
    private double cumulateDis;

    @SerializedName("index")
    private int index;

    @SerializedName(JsbMapKeyNames.H5_LOC_LAT)
    private double latitude;

    @SerializedName(JsbMapKeyNames.H5_LOC_LON)
    private double longitude;

    @SerializedName("pathIndex")
    private int pathIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GpsPoint(Parcel parcel) {
        this.longitude = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.altitude = parcel.readDouble();
        this.index = parcel.readInt();
        this.cumulateDis = parcel.readDouble();
        this.pathIndex = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.altitude);
        parcel.writeInt(this.index);
        parcel.writeDouble(this.cumulateDis);
        parcel.writeInt(this.pathIndex);
    }

    public GpsPoint(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public GpsPoint(double d, double d2, double d3) {
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public double getCumulateDis() {
        return this.cumulateDis;
    }

    public void setCumulateDis(double d) {
        this.cumulateDis = d;
    }

    public int getPathIndex() {
        return this.pathIndex;
    }

    public void setPathIndex(int i) {
        this.pathIndex = i;
    }

    public String toString() {
        return "GpsPoint{longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + ", index=" + this.index + ", cumulateDis=" + this.cumulateDis + ", pathIndex=" + this.pathIndex + '}';
    }
}
