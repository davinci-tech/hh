package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public final class zzaz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaz> CREATOR = new zzba();
    private final zzcq zzgj;
    private final Session zzz;

    zzaz(Session session, IBinder iBinder) {
        this.zzz = session;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzaz(Session session, zzcq zzcqVar) {
        Preconditions.checkArgument(session.getStartTime(TimeUnit.MILLISECONDS) < System.currentTimeMillis(), "Cannot start a session in the future");
        Preconditions.checkArgument(session.isOngoing(), "Cannot start a session which has already ended");
        this.zzz = session;
        this.zzgj = zzcqVar;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzaz) && Objects.equal(this.zzz, ((zzaz) obj).zzz);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzz);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzz, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
