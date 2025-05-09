package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class OfflineMapProvince extends Province {
    public static final Parcelable.Creator<OfflineMapProvince> CREATOR = new Parcelable.Creator<OfflineMapProvince>() { // from class: com.amap.api.maps.offlinemap.OfflineMapProvince.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ OfflineMapProvince createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ OfflineMapProvince[] newArray(int i) {
            return a(i);
        }

        private static OfflineMapProvince a(Parcel parcel) {
            return new OfflineMapProvince(parcel);
        }

        private static OfflineMapProvince[] a(int i) {
            return new OfflineMapProvince[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1455a;
    private int b;
    private long c;
    private String d;
    private int e;
    private ArrayList<OfflineMapCity> f;

    @Override // com.amap.api.maps.offlinemap.Province, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OfflineMapProvince() {
        this.b = 6;
        this.e = 0;
    }

    public String getUrl() {
        return this.f1455a;
    }

    public void setUrl(String str) {
        this.f1455a = str;
    }

    public int getState() {
        return this.b;
    }

    public void setState(int i) {
        this.b = i;
    }

    public long getSize() {
        return this.c;
    }

    public void setSize(long j) {
        this.c = j;
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

    @Override // com.amap.api.maps.offlinemap.Province, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1455a);
        parcel.writeInt(this.b);
        parcel.writeLong(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeTypedList(this.f);
    }

    public ArrayList<OfflineMapCity> getCityList() {
        ArrayList<OfflineMapCity> arrayList = this.f;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public ArrayList<OfflineMapCity> getDownloadedCityList() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        ArrayList<OfflineMapCity> arrayList2 = this.f;
        if (arrayList2 == null) {
            return arrayList;
        }
        Iterator<OfflineMapCity> it = arrayList2.iterator();
        while (it.hasNext()) {
            OfflineMapCity next = it.next();
            if (next.getState() != 6) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void setCityList(ArrayList<OfflineMapCity> arrayList) {
        this.f = arrayList;
    }

    public OfflineMapProvince(Parcel parcel) {
        super(parcel);
        this.b = 6;
        this.e = 0;
        this.f1455a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readLong();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.createTypedArrayList(OfflineMapCity.CREATOR);
    }
}
