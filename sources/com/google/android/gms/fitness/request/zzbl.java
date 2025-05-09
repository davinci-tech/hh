package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzbl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbl> CREATOR = new zzbm();
    private final String deviceAddress;
    private final zzcq zzgj;

    zzbl(String str, IBinder iBinder) {
        this.deviceAddress = str;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbl(String str, zzcq zzcqVar) {
        this.deviceAddress = str;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", this.deviceAddress);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.deviceAddress, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
