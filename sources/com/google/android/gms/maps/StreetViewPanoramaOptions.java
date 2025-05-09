package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

/* loaded from: classes8.dex */
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();
    private String panoId;
    private LatLng position;
    private Boolean zzak;
    private Boolean zzap;
    private StreetViewPanoramaCamera zzbw;
    private Integer zzbx;
    private Boolean zzby;
    private Boolean zzbz;
    private Boolean zzca;
    private StreetViewSource zzcb;

    public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.zzap = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStreetViewPanoramaCamera(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getPanoramaId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getPosition(), i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, getRadius(), false);
        SafeParcelWriter.writeByte(parcel, 6, com.google.android.gms.maps.internal.zza.zza(this.zzby));
        SafeParcelWriter.writeByte(parcel, 7, com.google.android.gms.maps.internal.zza.zza(this.zzap));
        SafeParcelWriter.writeByte(parcel, 8, com.google.android.gms.maps.internal.zza.zza(this.zzbz));
        SafeParcelWriter.writeByte(parcel, 9, com.google.android.gms.maps.internal.zza.zza(this.zzca));
        SafeParcelWriter.writeByte(parcel, 10, com.google.android.gms.maps.internal.zza.zza(this.zzak));
        SafeParcelWriter.writeParcelable(parcel, 11, getSource(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.zzby = Boolean.valueOf(z);
        return this;
    }

    public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.zzak = Boolean.valueOf(z);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", this.panoId).add("Position", this.position).add("Radius", this.zzbx).add("Source", this.zzcb).add("StreetViewPanoramaCamera", this.zzbw).add("UserNavigationEnabled", this.zzby).add("ZoomGesturesEnabled", this.zzap).add("PanningGesturesEnabled", this.zzbz).add("StreetNamesEnabled", this.zzca).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.zzca = Boolean.valueOf(z);
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzbx = num;
        this.zzcb = streetViewSource;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.position = latLng;
        this.zzbx = num;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng, StreetViewSource streetViewSource) {
        this.position = latLng;
        this.zzcb = streetViewSource;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latLng) {
        this.position = latLng;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaId(String str) {
        this.panoId = str;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zzbw = streetViewPanoramaCamera;
        return this;
    }

    public final StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.zzbz = Boolean.valueOf(z);
        return this;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final Boolean getUserNavigationEnabled() {
        return this.zzby;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzbw;
    }

    public final Boolean getStreetNamesEnabled() {
        return this.zzca;
    }

    public final StreetViewSource getSource() {
        return this.zzcb;
    }

    public final Integer getRadius() {
        return this.zzbx;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final String getPanoramaId() {
        return this.panoId;
    }

    public final Boolean getPanningGesturesEnabled() {
        return this.zzbz;
    }

    StreetViewPanoramaOptions(StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5, StreetViewSource streetViewSource) {
        this.zzby = true;
        this.zzap = true;
        this.zzbz = true;
        this.zzca = true;
        this.zzcb = StreetViewSource.DEFAULT;
        this.zzbw = streetViewPanoramaCamera;
        this.position = latLng;
        this.zzbx = num;
        this.panoId = str;
        this.zzby = com.google.android.gms.maps.internal.zza.zza(b);
        this.zzap = com.google.android.gms.maps.internal.zza.zza(b2);
        this.zzbz = com.google.android.gms.maps.internal.zza.zza(b3);
        this.zzca = com.google.android.gms.maps.internal.zza.zza(b4);
        this.zzak = com.google.android.gms.maps.internal.zza.zza(b5);
        this.zzcb = streetViewSource;
    }

    public StreetViewPanoramaOptions() {
        this.zzby = true;
        this.zzap = true;
        this.zzbz = true;
        this.zzca = true;
        this.zzcb = StreetViewSource.DEFAULT;
    }
}
