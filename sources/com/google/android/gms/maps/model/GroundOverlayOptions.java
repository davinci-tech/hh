package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private float bearing;
    private float height;
    private float width;
    private float zzcr;
    private boolean zzcs;
    private boolean zzct;
    private BitmapDescriptor zzcw;
    private LatLng zzcx;
    private LatLngBounds zzcy;
    private float zzcz;
    private float zzda;
    private float zzdb;

    public final GroundOverlayOptions zIndex(float f) {
        this.zzcr = f;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzcw.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getLocation(), i, false);
        SafeParcelWriter.writeFloat(parcel, 4, getWidth());
        SafeParcelWriter.writeFloat(parcel, 5, getHeight());
        SafeParcelWriter.writeParcelable(parcel, 6, getBounds(), i, false);
        SafeParcelWriter.writeFloat(parcel, 7, getBearing());
        SafeParcelWriter.writeFloat(parcel, 8, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeFloat(parcel, 10, getTransparency());
        SafeParcelWriter.writeFloat(parcel, 11, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final GroundOverlayOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final GroundOverlayOptions transparency(float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzcz = f;
        return this;
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        LatLng latLng = this.zzcx;
        boolean z = latLng == null;
        String valueOf = String.valueOf(latLng);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
        sb.append("Position has already been set using position: ");
        sb.append(valueOf);
        Preconditions.checkState(z, sb.toString());
        this.zzcy = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        Preconditions.checkState(this.zzcy == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f >= 0.0f, "Width must be non-negative");
        Preconditions.checkArgument(f2 >= 0.0f, "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public final GroundOverlayOptions position(LatLng latLng, float f) {
        Preconditions.checkState(this.zzcy == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f >= 0.0f, "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        Preconditions.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzcw = bitmapDescriptor;
        return this;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final LatLng getLocation() {
        return this.zzcx;
    }

    public final BitmapDescriptor getImage() {
        return this.zzcw;
    }

    public final float getHeight() {
        return this.height;
    }

    public final LatLngBounds getBounds() {
        return this.zzcy;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final GroundOverlayOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final GroundOverlayOptions bearing(float f) {
        this.bearing = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions anchor(float f, float f2) {
        this.zzda = f;
        this.zzdb = f2;
        return this;
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzcx = latLng;
        this.width = f;
        this.height = f2;
        return this;
    }

    GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zzcs = true;
        this.zzcz = 0.0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
        this.zzcw = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzcx = latLng;
        this.width = f;
        this.height = f2;
        this.zzcy = latLngBounds;
        this.bearing = f3;
        this.zzcr = f4;
        this.zzcs = z;
        this.zzcz = f5;
        this.zzda = f6;
        this.zzdb = f7;
        this.zzct = z2;
    }

    public GroundOverlayOptions() {
        this.zzcs = true;
        this.zzcz = 0.0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
    }
}
