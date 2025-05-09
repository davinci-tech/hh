package com.huawei.hms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;
import com.huawei.hms.maps.HuaweiMapOptions;
import java.math.BigDecimal;

/* loaded from: classes4.dex */
public class LatLngBounds implements Parcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new Parcelable.Creator<LatLngBounds>() { // from class: com.huawei.hms.maps.model.LatLngBounds.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLngBounds[] newArray(int i) {
            return new LatLngBounds[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLngBounds createFromParcel(Parcel parcel) {
            return new LatLngBounds(parcel);
        }
    };
    public final LatLng northeast;
    public final LatLng southwest;

    /* JADX INFO: Access modifiers changed from: private */
    public static double c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.southwest, i, false);
        parcelWrite.writeParcelable(3, this.northeast, i, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public final String toString() {
        return "LatLngBounds:southwest point is" + this.southwest + ",northeast point is" + this.northeast;
    }

    public final LatLngBounds including(LatLng latLng) {
        LatLng latLng2 = new LatLng(Math.min(this.southwest.latitude, latLng.latitude), this.southwest.longitude);
        LatLng latLng3 = new LatLng(Math.max(this.northeast.latitude, latLng.latitude), this.northeast.longitude);
        if (a(latLng.longitude)) {
            return new LatLngBounds(latLng2, latLng3);
        }
        latLng2.longitude = c(latLng2.longitude, latLng.longitude) < d(latLng3.longitude, latLng.longitude) ? latLng.longitude : latLng2.longitude;
        latLng3.longitude = c(latLng2.longitude, latLng.longitude) < d(latLng3.longitude, latLng.longitude) ? latLng3.longitude : latLng.longitude;
        return new LatLngBounds(latLng2, latLng3);
    }

    public final int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public final LatLng getCenter() {
        double doubleValue = new BigDecimal(this.southwest.latitude).add(new BigDecimal(this.northeast.latitude)).divide(new BigDecimal("2.0")).doubleValue();
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        if (this.southwest.longitude > d) {
            d += 360.0d;
        }
        return new LatLng(doubleValue, (d + d2) / 2.0d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public final boolean contains(LatLng latLng) {
        double d = latLng.latitude;
        return this.southwest.latitude <= d && d <= this.northeast.latitude && a(latLng.longitude);
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return HuaweiMapOptions.buildLatLngBounds(context, attributeSet);
    }

    public static Builder builder() {
        return new Builder();
    }

    private boolean a(double d) {
        return this.southwest.longitude <= this.northeast.longitude ? (this.southwest.longitude < d || Math.abs(this.southwest.longitude - d) < 1.0E-6d) && (d < this.northeast.longitude || Math.abs(d - this.northeast.longitude) < 1.0E-6d) : this.southwest.longitude < d || Math.abs(this.southwest.longitude - d) < 1.0E-6d || d < this.northeast.longitude || Math.abs(d - this.northeast.longitude) < 1.0E-6d;
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private double f4999a = Double.MAX_VALUE;
        private double b = -1.7976931348623157E308d;
        private double c = Double.NaN;
        private double d = Double.NaN;

        /* JADX WARN: Code restructure failed: missing block: B:12:0x004c, code lost:
        
            if (java.lang.Math.abs(r0 - r2) >= 1.0E-6d) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
        
            if (java.lang.Math.abs(r0 - r2) >= 1.0E-6d) goto L25;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.huawei.hms.maps.model.LatLngBounds.Builder include(com.huawei.hms.maps.model.LatLng r11) {
            /*
                r10 = this;
                double r0 = r10.f4999a
                double r2 = r11.latitude
                double r0 = java.lang.Math.min(r0, r2)
                r10.f4999a = r0
                double r0 = r10.b
                double r2 = r11.latitude
                double r0 = java.lang.Math.max(r0, r2)
                r10.b = r0
                double r0 = r11.longitude
                double r2 = r10.c
                double r4 = r10.d
                int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r6 = 1
                r7 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
                r9 = 0
                if (r11 < 0) goto L4f
                double r2 = r2 - r4
                double r2 = java.lang.Math.abs(r2)
                int r11 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r11 >= 0) goto L2f
                goto L4f
            L2f:
                double r2 = r10.c
                int r11 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r11 < 0) goto L71
                double r2 = r2 - r0
                double r2 = java.lang.Math.abs(r2)
                int r11 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r11 < 0) goto L71
                double r2 = r10.d
                int r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r11 < 0) goto L71
                double r2 = r0 - r2
                double r2 = java.lang.Math.abs(r2)
                int r11 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r11 >= 0) goto L6f
                goto L71
            L4f:
                double r2 = r10.c
                int r11 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r11 < 0) goto L5e
                double r2 = r2 - r0
                double r2 = java.lang.Math.abs(r2)
                int r11 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r11 >= 0) goto L6f
            L5e:
                double r2 = r10.d
                int r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r11 < 0) goto L71
                double r2 = r0 - r2
                double r2 = java.lang.Math.abs(r2)
                int r11 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r11 >= 0) goto L6f
                goto L71
            L6f:
                r11 = r9
                goto L72
            L71:
                r11 = r6
            L72:
                double r2 = r10.c
                double r2 = com.huawei.hms.maps.model.LatLngBounds.a(r2, r0)
                double r4 = r10.d
                double r4 = com.huawei.hms.maps.model.LatLngBounds.b(r4, r0)
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 >= 0) goto L83
                goto L84
            L83:
                r6 = r9
            L84:
                double r2 = r10.c
                boolean r2 = java.lang.Double.isNaN(r2)
                if (r2 == 0) goto L8f
                r10.c = r0
                goto L97
            L8f:
                if (r11 == 0) goto L92
                return r10
            L92:
                if (r6 == 0) goto L97
                r10.c = r0
                return r10
            L97:
                r10.d = r0
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.maps.model.LatLngBounds.Builder.include(com.huawei.hms.maps.model.LatLng):com.huawei.hms.maps.model.LatLngBounds$Builder");
        }

        public LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.c), "no points in the bounds");
            return new LatLngBounds(new LatLng(this.f4999a, this.c), new LatLng(this.b, this.d));
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "southwest can not be null");
        Preconditions.checkNotNull(latLng2, "northeast can not be null");
        if (!Double.isNaN(latLng2.latitude) && !Double.isNaN(latLng.latitude)) {
            Preconditions.checkArgument(latLng2.latitude >= latLng.latitude, "southern latitude >= northern latitude", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        }
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    protected LatLngBounds(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.southwest = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.northeast = (LatLng) parcelReader.readParcelable(3, LatLng.CREATOR, null);
    }
}
