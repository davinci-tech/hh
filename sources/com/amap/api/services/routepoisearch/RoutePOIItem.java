package com.amap.api.services.routepoisearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes8.dex */
public class RoutePOIItem implements Parcelable {
    public static final Parcelable.Creator<RoutePOIItem> CREATOR = new Parcelable.Creator<RoutePOIItem>() { // from class: com.amap.api.services.routepoisearch.RoutePOIItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RoutePOIItem createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RoutePOIItem[] newArray(int i) {
            return a(i);
        }

        private static RoutePOIItem a(Parcel parcel) {
            return new RoutePOIItem(parcel);
        }

        private static RoutePOIItem[] a(int i) {
            return new RoutePOIItem[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1588a;
    private String b;
    private LatLonPoint c;
    private float d;
    private float e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RoutePOIItem() {
    }

    public String getID() {
        return this.f1588a;
    }

    public void setID(String str) {
        this.f1588a = str;
    }

    public String getTitle() {
        return this.b;
    }

    public void setTitle(String str) {
        this.b = str;
    }

    public LatLonPoint getPoint() {
        return this.c;
    }

    public void setPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public float getDistance() {
        return this.d;
    }

    public void setDistance(float f) {
        this.d = f;
    }

    public float getDuration() {
        return this.e;
    }

    public void setDuration(float f) {
        this.e = f;
    }

    protected RoutePOIItem(Parcel parcel) {
        this.f1588a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1588a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
    }
}
