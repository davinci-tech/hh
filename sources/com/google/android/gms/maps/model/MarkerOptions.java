package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class MarkerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();
    private float alpha;
    private LatLng position;
    private float zzcr;
    private boolean zzcs;
    private float zzda;
    private float zzdb;
    private String zzdm;
    private String zzdn;
    private BitmapDescriptor zzdo;
    private boolean zzdp;
    private boolean zzdq;
    private float zzdr;
    private float zzds;
    private float zzdt;

    public final MarkerOptions zIndex(float f) {
        this.zzcr = f;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPosition(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeString(parcel, 4, getSnippet(), false);
        BitmapDescriptor bitmapDescriptor = this.zzdo;
        SafeParcelWriter.writeIBinder(parcel, 5, bitmapDescriptor == null ? null : bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloat(parcel, 6, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 8, isDraggable());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 10, isFlat());
        SafeParcelWriter.writeFloat(parcel, 11, getRotation());
        SafeParcelWriter.writeFloat(parcel, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(parcel, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(parcel, 14, getAlpha());
        SafeParcelWriter.writeFloat(parcel, 15, getZIndex());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final MarkerOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final MarkerOptions title(String str) {
        this.zzdm = str;
        return this;
    }

    public final MarkerOptions snippet(String str) {
        this.zzdn = str;
        return this;
    }

    public final MarkerOptions rotation(float f) {
        this.zzdr = f;
        return this;
    }

    public final MarkerOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        this.position = latLng;
        return this;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final boolean isFlat() {
        return this.zzdq;
    }

    public final boolean isDraggable() {
        return this.zzdp;
    }

    public final MarkerOptions infoWindowAnchor(float f, float f2) {
        this.zzds = f;
        this.zzdt = f2;
        return this;
    }

    public final MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.zzdo = bitmapDescriptor;
        return this;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final String getTitle() {
        return this.zzdm;
    }

    public final String getSnippet() {
        return this.zzdn;
    }

    public final float getRotation() {
        return this.zzdr;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzdt;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzds;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzdo;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final MarkerOptions flat(boolean z) {
        this.zzdq = z;
        return this;
    }

    public final MarkerOptions draggable(boolean z) {
        this.zzdp = z;
        return this;
    }

    public final MarkerOptions anchor(float f, float f2) {
        this.zzda = f;
        this.zzdb = f2;
        return this;
    }

    public final MarkerOptions alpha(float f) {
        this.alpha = f;
        return this;
    }

    MarkerOptions(LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6, float f7) {
        this.zzda = 0.5f;
        this.zzdb = 1.0f;
        this.zzcs = true;
        this.zzdq = false;
        this.zzdr = 0.0f;
        this.zzds = 0.5f;
        this.zzdt = 0.0f;
        this.alpha = 1.0f;
        this.position = latLng;
        this.zzdm = str;
        this.zzdn = str2;
        this.zzdo = iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzda = f;
        this.zzdb = f2;
        this.zzdp = z;
        this.zzcs = z2;
        this.zzdq = z3;
        this.zzdr = f3;
        this.zzds = f4;
        this.zzdt = f5;
        this.alpha = f6;
        this.zzcr = f7;
    }

    public MarkerOptions() {
        this.zzda = 0.5f;
        this.zzdb = 1.0f;
        this.zzcs = true;
        this.zzdq = false;
        this.zzdr = 0.0f;
        this.zzds = 0.5f;
        this.zzdt = 0.0f;
        this.alpha = 1.0f;
    }
}
