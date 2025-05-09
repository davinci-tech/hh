package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzaa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaa> CREATOR = new zzab();
    private final zzcq zzgj;

    zzaa(IBinder iBinder) {
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzaa(zzcq zzcqVar) {
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("DisableFitRequest", new Object[0]);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzgj.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
