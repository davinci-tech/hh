package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public class Doorway implements Parcelable {
    public static final Parcelable.Creator<Doorway> CREATOR = new Parcelable.Creator<Doorway>() { // from class: com.amap.api.services.route.Doorway.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Doorway[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Doorway createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static Doorway a(Parcel parcel) {
            return new Doorway(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1533a;
    private LatLonPoint b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.f1533a;
    }

    public void setName(String str) {
        this.f1533a = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.b;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1533a);
        parcel.writeParcelable(this.b, i);
    }

    public Doorway(Parcel parcel) {
        this.f1533a = parcel.readString();
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public Doorway() {
    }
}
