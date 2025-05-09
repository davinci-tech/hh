package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.col.p0003sl.dv;

/* loaded from: classes8.dex */
public final class VisibleRegion implements Parcelable {
    public static final VisibleRegionCreator CREATOR = new VisibleRegionCreator();
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    private final int mVersionCode;
    public final LatLng nearLeft;
    public final LatLng nearRight;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    VisibleRegion(int i, LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.mVersionCode = i;
        this.nearLeft = latLng;
        this.nearRight = latLng2;
        this.farLeft = latLng3;
        this.farRight = latLng4;
        this.latLngBounds = latLngBounds;
    }

    public VisibleRegion(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this(1, latLng, latLng2, latLng3, latLng4, latLngBounds);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        VisibleRegionCreator.a(this, parcel, i);
    }

    public final int hashCode() {
        return dv.a(new Object[]{this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds});
    }

    final int a() {
        return this.mVersionCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight) && this.farLeft.equals(visibleRegion.farLeft) && this.farRight.equals(visibleRegion.farRight) && this.latLngBounds.equals(visibleRegion.latLngBounds);
    }

    public final String toString() {
        return dv.a(dv.a("nearLeft", this.nearLeft), dv.a("nearRight", this.nearRight), dv.a("farLeft", this.farLeft), dv.a("farRight", this.farRight), dv.a("latLngBounds", this.latLngBounds));
    }
}
