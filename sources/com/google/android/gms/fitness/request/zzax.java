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
public final class zzax extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzax> CREATOR = new zzay();
    private final zzcq zzgj;
    private final PendingIntent zzhi;
    private final int zzii;

    zzax(PendingIntent pendingIntent, IBinder iBinder, int i) {
        this.zzhi = pendingIntent;
        this.zzgj = iBinder == null ? null : zzcr.zzj(iBinder);
        this.zzii = i;
    }

    public zzax(PendingIntent pendingIntent, zzcq zzcqVar, int i) {
        this.zzhi = pendingIntent;
        this.zzgj = zzcqVar;
        this.zzii = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhi, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzii);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzhi).add("sessionRegistrationOption", Integer.valueOf(this.zzii)).toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzax)) {
            return false;
        }
        zzax zzaxVar = (zzax) obj;
        return this.zzii == zzaxVar.zzii && Objects.equal(this.zzhi, zzaxVar.zzhi);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhi, Integer.valueOf(this.zzii));
    }
}
