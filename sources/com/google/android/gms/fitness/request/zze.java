package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    private final String deviceAddress;
    private final BleDevice zzgi;
    private final zzcq zzgj;

    zze(String str, BleDevice bleDevice, IBinder iBinder) {
        this.deviceAddress = str;
        this.zzgi = bleDevice;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zze(String str, BleDevice bleDevice, zzcq zzcqVar) {
        this.deviceAddress = str;
        this.zzgi = bleDevice;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", this.deviceAddress, this.zzgi);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.deviceAddress, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzgi, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
