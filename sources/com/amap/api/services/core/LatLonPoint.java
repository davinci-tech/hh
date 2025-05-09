package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class LatLonPoint implements Parcelable {
    public static final Parcelable.Creator<LatLonPoint> CREATOR = new Parcelable.Creator<LatLonPoint>() { // from class: com.amap.api.services.core.LatLonPoint.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ LatLonPoint createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ LatLonPoint[] newArray(int i) {
            return a(i);
        }

        private static LatLonPoint a(Parcel parcel) {
            return new LatLonPoint(parcel);
        }

        private static LatLonPoint[] a(int i) {
            return new LatLonPoint[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private double f1482a;
    private double b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLonPoint(double d, double d2) {
        this.f1482a = d;
        this.b = d2;
    }

    public double getLongitude() {
        return this.b;
    }

    public void setLongitude(double d) {
        this.b = d;
    }

    public double getLatitude() {
        return this.f1482a;
    }

    public void setLatitude(double d) {
        this.f1482a = d;
    }

    public LatLonPoint copy() {
        return new LatLonPoint(this.f1482a, this.b);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f1482a);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.b);
        return ((i + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLonPoint latLonPoint = (LatLonPoint) obj;
        return Double.doubleToLongBits(this.f1482a) == Double.doubleToLongBits(latLonPoint.f1482a) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(latLonPoint.b);
    }

    public String toString() {
        return this.f1482a + "," + this.b;
    }

    protected LatLonPoint(Parcel parcel) {
        this.f1482a = parcel.readDouble();
        this.b = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f1482a);
        parcel.writeDouble(this.b);
    }
}
