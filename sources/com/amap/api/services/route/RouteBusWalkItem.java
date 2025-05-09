package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public class RouteBusWalkItem extends WalkPath implements Parcelable {
    public static final Parcelable.Creator<RouteBusWalkItem> CREATOR = new Parcelable.Creator<RouteBusWalkItem>() { // from class: com.amap.api.services.route.RouteBusWalkItem.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ RouteBusWalkItem[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RouteBusWalkItem createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static RouteBusWalkItem a(Parcel parcel) {
            return new RouteBusWalkItem(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private LatLonPoint f1553a;
    private LatLonPoint b;

    @Override // com.amap.api.services.route.WalkPath, com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLonPoint getOrigin() {
        return this.f1553a;
    }

    public void setOrigin(LatLonPoint latLonPoint) {
        this.f1553a = latLonPoint;
    }

    public LatLonPoint getDestination() {
        return this.b;
    }

    public void setDestination(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    @Override // com.amap.api.services.route.WalkPath, com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f1553a, i);
        parcel.writeParcelable(this.b, i);
    }

    public RouteBusWalkItem(Parcel parcel) {
        super(parcel);
        this.f1553a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public RouteBusWalkItem() {
    }
}
