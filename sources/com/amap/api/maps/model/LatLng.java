package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.AMapException;
import com.huawei.operation.utils.Constants;

/* loaded from: classes2.dex */
public final class LatLng implements Parcelable, Cloneable {
    public static final LatLngCreator CREATOR = new LatLngCreator();
    public final double latitude;
    public final double longitude;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public LatLng(double d, double d2) {
        this(d, d2, true);
    }

    public LatLng(double d, double d2, boolean z) {
        if (z) {
            if (-180.0d <= d2 && d2 < 180.0d) {
                this.longitude = d2;
            } else {
                this.longitude = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
            }
            if (d < -90.0d || d > 90.0d) {
                try {
                    throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
                } catch (AMapException e) {
                    e.printStackTrace();
                }
            }
            this.latitude = Math.max(-90.0d, Math.min(90.0d, d));
            return;
        }
        this.latitude = d;
        this.longitude = d2;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final LatLng m84clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return ((i + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(latLng.longitude);
    }

    public final String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + Constants.RIGHT_BRACKET_ONLY;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
    }
}
