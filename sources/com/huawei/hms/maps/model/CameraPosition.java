package com.huawei.hms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;
import com.huawei.hms.maps.HuaweiMapOptions;

/* loaded from: classes4.dex */
public class CameraPosition implements Parcelable {
    public static final float BEARING_360 = 360.0f;
    public static final Parcelable.Creator<CameraPosition> CREATOR = new Parcelable.Creator<CameraPosition>() { // from class: com.huawei.hms.maps.model.CameraPosition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraPosition[] newArray(int i) {
            return new CameraPosition[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraPosition createFromParcel(Parcel parcel) {
            return new CameraPosition(parcel);
        }
    };
    public static final int TILT_90 = 90;
    public final float bearing;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.target, i, false);
        parcelWrite.writeFloat(3, this.zoom);
        parcelWrite.writeFloat(4, this.tilt);
        parcelWrite.writeFloat(5, this.bearing);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public final String toString() {
        return getClass().getSimpleName() + ":{bearing=" + this.bearing + ",zoom=" + this.zoom + ",tilt=" + this.tilt + ",target=" + this.target + "}";
    }

    public final int hashCode() {
        return Objects.hashCode(this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private LatLng f4980a;
        private float b;
        private float c;
        private float d;

        public Builder zoom(float f) {
            this.b = f;
            return this;
        }

        public Builder tilt(float f) {
            this.c = f;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.f4980a = latLng;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(this.f4980a, this.b, this.c, this.d);
        }

        public Builder bearing(float f) {
            this.d = f;
            return this;
        }

        public Builder(CameraPosition cameraPosition) {
            this.f4980a = cameraPosition.target;
            this.b = cameraPosition.zoom;
            this.c = cameraPosition.tilt;
            this.d = cameraPosition.bearing;
        }

        public Builder() {
        }
    }

    public static CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public static CameraPosition createFromAttributes(Context context, AttributeSet attributeSet) {
        return HuaweiMapOptions.buildCameraPosition(context, attributeSet);
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        if (latLng == null) {
            throw new NullPointerException("camera target is null");
        }
        this.target = latLng;
        this.zoom = f;
        if (f2 < 0.0f || f2 > 90.0f) {
            throw new IllegalArgumentException("Tilt needs to be between 0 and 90 inclusive");
        }
        this.tilt = f2 + 0.0f;
        this.bearing = (((double) f3) <= 0.0d ? (f3 % 360.0f) + 360.0f : f3) % 360.0f;
    }

    protected CameraPosition(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.target = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.zoom = parcelReader.readFloat(3, 0.0f);
        this.tilt = parcelReader.readFloat(4, 0.0f);
        this.bearing = parcelReader.readFloat(5, 0.0f);
    }
}
