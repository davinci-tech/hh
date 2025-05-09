package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class City implements Parcelable {
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() { // from class: com.amap.api.maps.offlinemap.City.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ City createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ City[] newArray(int i) {
            return a(i);
        }

        private static City a(Parcel parcel) {
            return new City(parcel);
        }

        private static City[] a(int i) {
            return new City[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1443a;
    private String b;
    private String c;
    private String d;
    private String e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public City() {
        this.f1443a = "";
        this.b = "";
        this.e = "";
    }

    public void setCity(String str) {
        this.f1443a = str;
    }

    public String getCity() {
        return this.f1443a;
    }

    public void setCode(String str) {
        if (str == null || "[]".equals(str)) {
            return;
        }
        this.b = str;
    }

    public String getCode() {
        return this.b;
    }

    public String getJianpin() {
        return this.c;
    }

    public void setJianpin(String str) {
        this.c = str;
    }

    public String getPinyin() {
        return this.d;
    }

    public void setPinyin(String str) {
        this.d = str;
    }

    public String getAdcode() {
        return this.e;
    }

    public void setAdcode(String str) {
        this.e = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1443a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    public City(Parcel parcel) {
        this.f1443a = "";
        this.b = "";
        this.e = "";
        this.f1443a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
