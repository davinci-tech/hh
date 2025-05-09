package com.google.android.gms.fitness.data;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zzf implements com.google.android.gms.internal.fitness.zzg<DataPoint, DataType> {
    public static final zzf zzam = new zzf();

    private zzf() {
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final com.google.android.gms.internal.fitness.zzh<DataType> zzb() {
        return zzg.zzan;
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ double zza(DataPoint dataPoint, int i) {
        return dataPoint.zzb(i).asFloat();
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ int zzb(DataPoint dataPoint, int i) {
        return dataPoint.zzb(i).asInt();
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ boolean zzc(DataPoint dataPoint, int i) {
        return dataPoint.zzb(i).isSet();
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ long zza(DataPoint dataPoint, TimeUnit timeUnit) {
        DataPoint dataPoint2 = dataPoint;
        return dataPoint2.getEndTime(timeUnit) - dataPoint2.getStartTime(timeUnit);
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ DataType zza(DataPoint dataPoint) {
        return dataPoint.getDataType();
    }

    @Override // com.google.android.gms.internal.fitness.zzg
    public final /* synthetic */ String zzb(DataPoint dataPoint) {
        return dataPoint.getDataType().getName();
    }
}
