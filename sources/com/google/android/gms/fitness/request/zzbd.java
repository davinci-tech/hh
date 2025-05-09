package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes8.dex */
public final class zzbd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbd> CREATOR = new zzbe();
    private final zzcq zzgj;
    private final PendingIntent zzhi;

    zzbd(PendingIntent pendingIntent, IBinder iBinder) {
        this.zzhi = pendingIntent;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbd(PendingIntent pendingIntent, zzcq zzcqVar) {
        this.zzhi = pendingIntent;
        this.zzgj = zzcqVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhi, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzhi).toString();
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof zzbd) && Objects.equal(this.zzhi, ((zzbd) obj).zzhi);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhi);
    }
}
