package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class OfflineMapCity extends City {
    public static final Parcelable.Creator<OfflineMapCity> CREATOR = new Parcelable.Creator<OfflineMapCity>() { // from class: com.amap.api.maps.offlinemap.OfflineMapCity.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ OfflineMapCity createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ OfflineMapCity[] newArray(int i) {
            return a(i);
        }

        private static OfflineMapCity a(Parcel parcel) {
            return new OfflineMapCity(parcel);
        }

        private static OfflineMapCity[] a(int i) {
            return new OfflineMapCity[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1446a;
    private long b;
    private int c;
    private String d;
    private int e;

    @Override // com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OfflineMapCity() {
        this.f1446a = "";
        this.b = 0L;
        this.c = 6;
        this.d = "";
        this.e = 0;
    }

    public String getUrl() {
        return this.f1446a;
    }

    public void setUrl(String str) {
        this.f1446a = str;
    }

    public long getSize() {
        return this.b;
    }

    public void setSize(long j) {
        this.b = j;
    }

    public int getState() {
        return this.c;
    }

    public void setState(int i) {
        this.c = i;
    }

    public String getVersion() {
        return this.d;
    }

    public void setVersion(String str) {
        this.d = str;
    }

    public int getcompleteCode() {
        return this.e;
    }

    public void setCompleteCode(int i) {
        this.e = i;
    }

    @Override // com.amap.api.maps.offlinemap.City, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1446a);
        parcel.writeLong(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
    }

    public OfflineMapCity(Parcel parcel) {
        super(parcel);
        this.f1446a = "";
        this.b = 0L;
        this.c = 6;
        this.d = "";
        this.e = 0;
        this.f1446a = parcel.readString();
        this.b = parcel.readLong();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
    }
}
