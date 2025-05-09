package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class RawDataPoint extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzz();
    private final long zzao;
    private final long zzap;
    private final Value[] zzaq;
    private final long zzas;
    private final long zzat;
    private final int zzdw;
    private final int zzdx;

    public RawDataPoint(long j, long j2, Value[] valueArr, int i, int i2, long j3, long j4) {
        this.zzao = j;
        this.zzap = j2;
        this.zzdw = i;
        this.zzdx = i2;
        this.zzas = j3;
        this.zzat = j4;
        this.zzaq = valueArr;
    }

    RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        this.zzao = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.zzap = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.zzaq = dataPoint.zzc();
        this.zzdw = com.google.android.gms.internal.fitness.zzf.zza(dataPoint.getDataSource(), list);
        this.zzdx = com.google.android.gms.internal.fitness.zzf.zza(dataPoint.zzd(), list);
        this.zzas = dataPoint.zze();
        this.zzat = dataPoint.zzf();
    }

    public final long getTimestampNanos() {
        return this.zzao;
    }

    public final long zzn() {
        return this.zzap;
    }

    public final Value[] zzc() {
        return this.zzaq;
    }

    public final int zzo() {
        return this.zzdw;
    }

    public final int zzp() {
        return this.zzdx;
    }

    public final long zze() {
        return this.zzas;
    }

    public final long zzf() {
        return this.zzat;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataPoint)) {
            return false;
        }
        RawDataPoint rawDataPoint = (RawDataPoint) obj;
        return this.zzao == rawDataPoint.zzao && this.zzap == rawDataPoint.zzap && Arrays.equals(this.zzaq, rawDataPoint.zzaq) && this.zzdw == rawDataPoint.zzdw && this.zzdx == rawDataPoint.zzdx && this.zzas == rawDataPoint.zzas;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzao), Long.valueOf(this.zzap));
    }

    public final String toString() {
        return String.format(Locale.US, "RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.zzaq), Long.valueOf(this.zzap), Long.valueOf(this.zzao), Integer.valueOf(this.zzdw), Integer.valueOf(this.zzdx));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzao);
        SafeParcelWriter.writeLong(parcel, 2, this.zzap);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzaq, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdx);
        SafeParcelWriter.writeLong(parcel, 6, this.zzas);
        SafeParcelWriter.writeLong(parcel, 7, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
