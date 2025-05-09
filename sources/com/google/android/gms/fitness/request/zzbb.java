package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzco;

/* loaded from: classes8.dex */
public final class zzbb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbb> CREATOR = new zzbc();
    private final String name;
    private final String zzdz;
    private final zzcn zzij;

    zzbb(String str, String str2, IBinder iBinder) {
        this.name = str;
        this.zzdz = str2;
        this.zzij = zzco.zzi(iBinder);
    }

    public zzbb(String str, String str2, zzcn zzcnVar) {
        this.name = str;
        this.zzdz = str2;
        this.zzij = zzcnVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbb)) {
            return false;
        }
        zzbb zzbbVar = (zzbb) obj;
        return Objects.equal(this.name, zzbbVar.name) && Objects.equal(this.zzdz, zzbbVar.zzdz);
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzdz);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("identifier", this.zzdz).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzdz, false);
        zzcn zzcnVar = this.zzij;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
