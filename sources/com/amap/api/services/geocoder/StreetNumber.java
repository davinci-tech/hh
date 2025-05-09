package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public final class StreetNumber implements Parcelable {
    public static final Parcelable.Creator<StreetNumber> CREATOR = new Parcelable.Creator<StreetNumber>() { // from class: com.amap.api.services.geocoder.StreetNumber.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StreetNumber[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StreetNumber createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static StreetNumber a(Parcel parcel) {
            return new StreetNumber(parcel, (byte) 0);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1502a;
    private String b;
    private LatLonPoint c;
    private String d;
    private float e;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    /* synthetic */ StreetNumber(Parcel parcel, byte b) {
        this(parcel);
    }

    public StreetNumber() {
    }

    public final String getStreet() {
        return this.f1502a;
    }

    public final void setStreet(String str) {
        this.f1502a = str;
    }

    public final String getNumber() {
        return this.b;
    }

    public final void setNumber(String str) {
        this.b = str;
    }

    public final LatLonPoint getLatLonPoint() {
        return this.c;
    }

    public final void setLatLonPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public final String getDirection() {
        return this.d;
    }

    public final void setDirection(String str) {
        this.d = str;
    }

    public final float getDistance() {
        return this.e;
    }

    public final void setDistance(float f) {
        this.e = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1502a);
        parcel.writeString(this.b);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }

    private StreetNumber(Parcel parcel) {
        this.f1502a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readFloat();
    }
}
