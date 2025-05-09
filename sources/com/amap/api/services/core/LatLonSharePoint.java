package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class LatLonSharePoint extends LatLonPoint implements Parcelable {
    public static final Parcelable.Creator<LatLonSharePoint> CREATOR = new Parcelable.Creator<LatLonSharePoint>() { // from class: com.amap.api.services.core.LatLonSharePoint.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ LatLonSharePoint createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ LatLonSharePoint[] newArray(int i) {
            return a(i);
        }

        private static LatLonSharePoint a(Parcel parcel) {
            return new LatLonSharePoint(parcel);
        }

        private static LatLonSharePoint[] a(int i) {
            return new LatLonSharePoint[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1483a;

    @Override // com.amap.api.services.core.LatLonPoint, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLonSharePoint(double d, double d2, String str) {
        super(d, d2);
        this.f1483a = str;
    }

    public String getSharePointName() {
        return this.f1483a;
    }

    public void setSharePointName(String str) {
        this.f1483a = str;
    }

    protected LatLonSharePoint(Parcel parcel) {
        super(parcel);
        this.f1483a = parcel.readString();
    }

    @Override // com.amap.api.services.core.LatLonPoint, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1483a);
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public int hashCode() {
        int hashCode = super.hashCode();
        String str = this.f1483a;
        return (hashCode * 31) + (str == null ? 0 : str.hashCode());
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        LatLonSharePoint latLonSharePoint = (LatLonSharePoint) obj;
        String str = this.f1483a;
        if (str == null) {
            if (latLonSharePoint.f1483a != null) {
                return false;
            }
        } else if (!str.equals(latLonSharePoint.f1483a)) {
            return false;
        }
        return true;
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public String toString() {
        return super.toString() + "," + this.f1483a;
    }
}
