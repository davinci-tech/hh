package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public final class GeocodeAddress implements Parcelable {
    public static final Parcelable.Creator<GeocodeAddress> CREATOR = new Parcelable.Creator<GeocodeAddress>() { // from class: com.amap.api.services.geocoder.GeocodeAddress.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ GeocodeAddress[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ GeocodeAddress createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static GeocodeAddress a(Parcel parcel) {
            return new GeocodeAddress(parcel, (byte) 0);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1494a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private LatLonPoint i;
    private String j;
    private String k;
    private String l;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    /* synthetic */ GeocodeAddress(Parcel parcel, byte b) {
        this(parcel);
    }

    public GeocodeAddress() {
    }

    public final String getFormatAddress() {
        return this.f1494a;
    }

    public final void setFormatAddress(String str) {
        this.f1494a = str;
    }

    public final String getProvince() {
        return this.b;
    }

    public final void setProvince(String str) {
        this.b = str;
    }

    public final String getCity() {
        return this.c;
    }

    public final void setCity(String str) {
        this.c = str;
    }

    public final String getDistrict() {
        return this.d;
    }

    public final void setDistrict(String str) {
        this.d = str;
    }

    public final String getTownship() {
        return this.e;
    }

    public final void setTownship(String str) {
        this.e = str;
    }

    public final String getNeighborhood() {
        return this.f;
    }

    public final void setNeighborhood(String str) {
        this.f = str;
    }

    public final String getBuilding() {
        return this.g;
    }

    public final void setBuilding(String str) {
        this.g = str;
    }

    public final String getAdcode() {
        return this.h;
    }

    public final void setAdcode(String str) {
        this.h = str;
    }

    public final LatLonPoint getLatLonPoint() {
        return this.i;
    }

    public final void setLatLonPoint(LatLonPoint latLonPoint) {
        this.i = latLonPoint;
    }

    public final String getLevel() {
        return this.j;
    }

    public final void setLevel(String str) {
        this.j = str;
    }

    public final String getCountry() {
        return this.k;
    }

    public final void setCountry(String str) {
        this.k = str;
    }

    public final String getPostcode() {
        return this.l;
    }

    public final void setPostcode(String str) {
        this.l = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1494a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeValue(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
    }

    private GeocodeAddress(Parcel parcel) {
        this.f1494a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
    }
}
