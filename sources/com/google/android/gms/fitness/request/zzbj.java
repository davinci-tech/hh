package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzbj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbj> CREATOR = new zzbk();
    private final zzcq zzgj;
    private Subscription zzio;
    private final boolean zzip;

    zzbj(Subscription subscription, boolean z, IBinder iBinder) {
        this.zzio = subscription;
        this.zzip = z;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbj(Subscription subscription, boolean z, zzcq zzcqVar) {
        this.zzio = subscription;
        this.zzip = false;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("subscription", this.zzio).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzio, i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzip);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
