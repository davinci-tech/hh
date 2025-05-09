package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.DPoint;
import java.util.List;

/* loaded from: classes8.dex */
public class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() { // from class: com.amap.api.fence.DistrictItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistrictItem createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistrictItem[] newArray(int i) {
            return a(i);
        }

        private static DistrictItem a(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        private static DistrictItem[] a(int i) {
            return new DistrictItem[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1382a;
    private String b;
    private String c;
    private List<DPoint> d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCitycode() {
        return this.b;
    }

    public void setCitycode(String str) {
        this.b = str;
    }

    public String getAdcode() {
        return this.c;
    }

    public void setAdcode(String str) {
        this.c = str;
    }

    public List<DPoint> getPolyline() {
        return this.d;
    }

    public void setPolyline(List<DPoint> list) {
        this.d = list;
    }

    public String getDistrictName() {
        return this.f1382a;
    }

    public void setDistrictName(String str) {
        this.f1382a = str;
    }

    public static Parcelable.Creator<DistrictItem> getCreator() {
        return CREATOR;
    }

    public DistrictItem() {
        this.f1382a = "";
        this.b = null;
        this.c = null;
        this.d = null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1382a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeTypedList(this.d);
    }

    protected DistrictItem(Parcel parcel) {
        this.f1382a = "";
        this.b = null;
        this.c = null;
        this.d = null;
        this.f1382a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.createTypedArrayList(DPoint.CREATOR);
    }
}
