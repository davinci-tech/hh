package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
    private final int zzar;
    private final int zzas;
    private final long zzat;
    private final long zzbt;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzas);
        SafeParcelWriter.writeInt(parcel, 2, this.zzar);
        SafeParcelWriter.writeLong(parcel, 3, this.zzbt);
        SafeParcelWriter.writeLong(parcel, 4, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return "NetworkLocationStatus: Wifi status: " + this.zzas + " Cell status: " + this.zzar + " elapsed time NS: " + this.zzat + " system time ms: " + this.zzbt;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), Long.valueOf(this.zzbt));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzaj zzajVar = (zzaj) obj;
        return this.zzas == zzajVar.zzas && this.zzar == zzajVar.zzar && this.zzbt == zzajVar.zzbt && this.zzat == zzajVar.zzat;
    }

    zzaj(int i, int i2, long j, long j2) {
        this.zzas = i;
        this.zzar = i2;
        this.zzbt = j;
        this.zzat = j2;
    }
}
