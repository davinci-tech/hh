package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public class BusinessArea implements Parcelable {
    public static final Parcelable.Creator<BusinessArea> CREATOR = new Parcelable.Creator<BusinessArea>() { // from class: com.amap.api.services.geocoder.BusinessArea.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BusinessArea createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BusinessArea[] newArray(int i) {
            return a(i);
        }

        private static BusinessArea a(Parcel parcel) {
            return new BusinessArea(parcel);
        }

        private static BusinessArea[] a(int i) {
            return new BusinessArea[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private LatLonPoint f1493a;
    private String b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BusinessArea() {
    }

    public LatLonPoint getCenterPoint() {
        return this.f1493a;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f1493a = latLonPoint;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1493a, i);
        parcel.writeString(this.b);
    }

    public BusinessArea(Parcel parcel) {
        this.f1493a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = parcel.readString();
    }
}
