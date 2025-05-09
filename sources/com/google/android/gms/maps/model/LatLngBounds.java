package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes2.dex */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
    public final LatLng northeast;
    public final LatLng southwest;

    /* JADX INFO: Access modifiers changed from: private */
    public static double zza(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zzb(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.southwest, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.northeast, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public final LatLngBounds including(LatLng latLng) {
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (!zza(d3)) {
            if (zza(d2, d3) < zzb(d, d3)) {
                d2 = d3;
            } else {
                d = d3;
            }
        }
        return new LatLngBounds(new LatLng(min, d2), new LatLng(max, d));
    }

    public final int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public final LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        if (d3 > d2) {
            d2 += 360.0d;
        }
        return new LatLng(d, (d2 + d3) / 2.0d);
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
        return this.southwest.latitude <= d && d <= this.northeast.latitude && zza(latLng.longitude);
    }

    private final boolean zza(double d) {
        return this.southwest.longitude <= this.northeast.longitude ? this.southwest.longitude <= d && d <= this.northeast.longitude : this.southwest.longitude <= d || d <= this.northeast.longitude;
    }

    public static final class Builder {
        private double zzdg = Double.POSITIVE_INFINITY;
        private double zzdh = Double.NEGATIVE_INFINITY;
        private double zzdi = Double.NaN;
        private double zzdj = Double.NaN;

        public final Builder include(LatLng latLng) {
            this.zzdg = Math.min(this.zzdg, latLng.latitude);
            this.zzdh = Math.max(this.zzdh, latLng.latitude);
            double d = latLng.longitude;
            if (!Double.isNaN(this.zzdi)) {
                double d2 = this.zzdi;
                double d3 = this.zzdj;
                if (d2 > d3 ? !(d2 <= d || d <= d3) : !(d2 <= d && d <= d3)) {
                    if (LatLngBounds.zza(d2, d) < LatLngBounds.zzb(this.zzdj, d)) {
                        this.zzdi = d;
                    }
                }
                return this;
            }
            this.zzdi = d;
            this.zzdj = d;
            return this;
        }

        public final LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzdi), "no included points");
            return new LatLngBounds(new LatLng(this.zzdg, this.zzdi), new LatLng(this.zzdh, this.zzdj));
        }
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zza(context, attributeSet);
    }

    public static Builder builder() {
        return new Builder();
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "null southwest");
        Preconditions.checkNotNull(latLng2, "null northeast");
        Preconditions.checkArgument(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }
}
