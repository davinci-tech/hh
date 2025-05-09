package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    private boolean zzt;
    private long zzu;
    private float zzv;
    private long zzw;
    private int zzx;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzt);
        SafeParcelWriter.writeLong(parcel, 2, this.zzu);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzv);
        SafeParcelWriter.writeLong(parcel, 4, this.zzw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeviceOrientationRequest[mShouldUseMag=");
        sb.append(this.zzt);
        sb.append(" mMinimumSamplingPeriodMs=");
        sb.append(this.zzu);
        sb.append(" mSmallestAngleChangeRadians=");
        sb.append(this.zzv);
        long j = this.zzw;
        if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j - elapsedRealtime);
            sb.append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zzx);
        }
        sb.append(']');
        return sb.toString();
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzt), Long.valueOf(this.zzu), Float.valueOf(this.zzv), Long.valueOf(this.zzw), Integer.valueOf(this.zzx));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzjVar = (zzj) obj;
        return this.zzt == zzjVar.zzt && this.zzu == zzjVar.zzu && Float.compare(this.zzv, zzjVar.zzv) == 0 && this.zzw == zzjVar.zzw && this.zzx == zzjVar.zzx;
    }

    zzj(boolean z, long j, float f, long j2, int i) {
        this.zzt = z;
        this.zzu = j;
        this.zzv = f;
        this.zzw = j2;
        this.zzx = i;
    }

    public zzj() {
        this(true, 50L, 0.0f, LocationRequestCompat.PASSIVE_INTERVAL, Integer.MAX_VALUE);
    }
}
