package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private int zzcg;
    private zzm zzch;
    private com.google.android.gms.location.zzr zzci;
    private zzaj zzcj;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzch, i, false);
        com.google.android.gms.location.zzr zzrVar = this.zzci;
        SafeParcelWriter.writeIBinder(parcel, 3, zzrVar == null ? null : zzrVar.asBinder(), false);
        zzaj zzajVar = this.zzcj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzajVar != null ? zzajVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    zzo(int i, zzm zzmVar, IBinder iBinder, IBinder iBinder2) {
        this.zzcg = i;
        this.zzch = zzmVar;
        zzaj zzajVar = null;
        this.zzci = iBinder == null ? null : com.google.android.gms.location.zzs.zza(iBinder);
        if (iBinder2 != null && iBinder2 != null) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzajVar = queryLocalInterface instanceof zzaj ? (zzaj) queryLocalInterface : new zzal(iBinder2);
        }
        this.zzcj = zzajVar;
    }
}
