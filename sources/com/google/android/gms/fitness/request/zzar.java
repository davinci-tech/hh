package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    private final zzcq zzgj;
    private final PendingIntent zzhi;
    private final com.google.android.gms.fitness.data.zzt zzhr;

    zzar(IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2) {
        this.zzhr = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zza(iBinder);
        this.zzhi = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzar(com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, zzcq zzcqVar) {
        this.zzhr = zztVar;
        this.zzhi = pendingIntent;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.zzhr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        com.google.android.gms.fitness.data.zzt zztVar = this.zzhr;
        SafeParcelWriter.writeIBinder(parcel, 1, zztVar == null ? null : zztVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhi, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar != null ? zzcqVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
