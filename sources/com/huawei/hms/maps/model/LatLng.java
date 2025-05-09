package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.maps.utils.LogM;
import java.util.Objects;

/* loaded from: classes4.dex */
public class LatLng implements Parcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: com.huawei.hms.maps.model.LatLng.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LatLng[] newArray(int i) {
            return new LatLng[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LatLng createFromParcel(Parcel parcel) {
            return new LatLng(parcel);
        }
    };
    public double latitude;
    public double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeDouble(2, this.latitude);
        parcelWrite.writeDouble(3, this.longitude);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public final String toString() {
        return "latitude: " + this.latitude + " longitude: " + this.longitude;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.latitude), Double.valueOf(this.longitude));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.compare(latLng.latitude, this.latitude) == 0 && Double.compare(latLng.longitude, this.longitude) == 0;
    }

    protected LatLng(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.latitude = parcelReader.readDouble(2, 0.0d);
        this.longitude = parcelReader.readDouble(3, 0.0d);
    }

    public LatLng(double d, double d2) {
        if ((-180.0d >= d2 || d2 >= 180.0d) && Math.abs(d2 + 180.0d) >= 1.0E-6d) {
            this.longitude = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
            LogM.e("LatLng", "longitude is out of valid range, ,using modulo operation.");
        } else {
            this.longitude = d2;
        }
        this.latitude = Math.max(-90.0d, Math.min(90.0d, d));
    }
}
