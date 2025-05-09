package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

/* loaded from: classes8.dex */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
    private float zzcr;
    private boolean zzcs;
    private float zzcz;
    private zzaf zzeh;
    private TileProvider zzei;
    private boolean zzej;

    public final TileOverlayOptions zIndex(float f) {
        this.zzcr = f;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzeh.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzcz = f;
        return this;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzei = tileProvider;
        this.zzeh = tileProvider == null ? null : new zzt(this, tileProvider);
        return this;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final TileProvider getTileProvider() {
        return this.zzei;
    }

    public final boolean getFadeIn() {
        return this.zzej;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzej = z;
        return this;
    }

    TileOverlayOptions(IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0.0f;
        zzaf zzk = zzag.zzk(iBinder);
        this.zzeh = zzk;
        this.zzei = zzk == null ? null : new zzs(this);
        this.zzcs = z;
        this.zzcr = f;
        this.zzej = z2;
        this.zzcz = f2;
    }

    public TileOverlayOptions() {
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0.0f;
    }
}
