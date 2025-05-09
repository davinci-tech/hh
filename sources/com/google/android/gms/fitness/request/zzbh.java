package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzbh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbh> CREATOR = new zzbi();
    private final zzcq zzgj;
    private final zzae zzik;

    zzbh(IBinder iBinder, IBinder iBinder2) {
        zzae zzagVar;
        if (iBinder == null) {
            zzagVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            zzagVar = queryLocalInterface instanceof zzae ? (zzae) queryLocalInterface : new zzag(iBinder);
        }
        this.zzik = zzagVar;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzbh(zzae zzaeVar, zzcq zzcqVar) {
        this.zzik = zzaeVar;
        this.zzgj = zzcqVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzik.asBinder(), false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
