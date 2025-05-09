package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes2.dex */
public final class zzb extends AbstractSafeParcelable {
    private final String packageName;
    private final String zzae;
    public static final zzb zzad = new zzb("com.google.android.gms", null);
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();

    public static zzb zza(String str) {
        return "com.google.android.gms".equals(str) ? zzad : new zzb(str, null);
    }

    public zzb(String str, String str2) {
        this.packageName = (String) Preconditions.checkNotNull(str);
        this.zzae = str2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzb)) {
            return false;
        }
        zzb zzbVar = (zzb) obj;
        return this.packageName.equals(zzbVar.packageName) && Objects.equal(this.zzae, zzbVar.zzae);
    }

    public final int hashCode() {
        return Objects.hashCode(this.packageName, this.zzae);
    }

    public final String toString() {
        return String.format("Application{%s:%s}", this.packageName, this.zzae);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzae, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
