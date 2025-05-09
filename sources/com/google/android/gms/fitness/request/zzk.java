package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* loaded from: classes2.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    private final DataSet zzeb;
    private final zzcq zzgj;
    private final boolean zzgq;

    zzk(DataSet dataSet, IBinder iBinder, boolean z) {
        this.zzeb = dataSet;
        this.zzgj = zzcr.zzj(iBinder);
        this.zzgq = z;
    }

    public zzk(DataSet dataSet, zzcq zzcqVar, boolean z) {
        this.zzeb = dataSet;
        this.zzgj = zzcqVar;
        this.zzgq = z;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzk) && Objects.equal(this.zzeb, ((zzk) obj).zzeb);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzeb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataSet", this.zzeb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzeb, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzgq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
